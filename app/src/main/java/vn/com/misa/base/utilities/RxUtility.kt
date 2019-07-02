package vn.com.misa.base.utilities

import android.util.Log
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableObserver
import io.reactivex.schedulers.Schedulers
import vn.com.base.libraries.loggers.Logger
import vn.com.base.libraries.rxbus.IEvent
import vn.com.base.libraries.rxbus.RxBus
import vn.com.base.libraries.utilities.OptionalUtility
import vn.com.misa.base.commons.views.IBaseMvpView
import vn.com.misa.base.listeners.IApiCallback
import vn.com.misa.base.models.BaseResponse
import vn.com.misa.base.models.events.NetworkEvent
import java.util.concurrent.TimeUnit

fun <T> Observable<T>.delayEach(interval: Long, timeUnit: TimeUnit): Observable<T> =
    this.concatMap { i -> Observable.just(i).delay(interval, timeUnit) }

class RxUtility {
    companion object {
        private const val TAG = "RxUtility"

        /**
         * Use to get api restful using retrofit
         *
         * @param v           is use to get lifecycle of fragment
         * @param observable  is response of retrofit when request api
         * @param apiCallback is use to callback data to ui view
         * @param <T>         is class parse response
         * @return Disposable to cancel subscribe with lifecycle onDestroy of fragment */
        fun <T> async(
            v: IBaseMvpView, observable: Observable<BaseResponse<T>>,
            apiCallback: IApiCallback<T>? = null
        ) {
            if (!v.isNetworkAvailable()) {
                v.rxBus.send(NetworkEvent(ErrorCodes.NO_INTERNET))
                return
            }

            // bind to lifecycle
//            when (v) {
//                is IBaseFragmentMvpView -> observable.compose(v.bindUntilEvent(FragmentEvent.DESTROY_VIEW))
//                is IBaseActivityMvpView -> observable.compose(v.bindUntilEvent(ActivityEvent.DESTROY))
//            }

            // start service
            apiCallback?.onStart()
            v.getCompositeDisposable().add(observable
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(object : DisposableObserver<BaseResponse<T>>() {
                    override fun onNext(response: BaseResponse<T>) {
                        OptionalUtility.with(response).doIfPresent { r ->
                            if (r.success) {
                                apiCallback?.onSuccess(r.data)
                            } else {
                                val error = ErrorUtility.getBaseErrorText(
                                    v.appContext,
                                    v.rxBus,
                                    ErrorUtility.getErrorBodyApp(r.message)
                                )
                                Logger.e(TAG, "onError:: $error")
                                apiCallback?.onError(error)
                            }
                        }
                    }

                    override fun onError(e: Throwable) {
                        val error =
                            ErrorUtility.getBaseErrorText(v.appContext, v.rxBus, ErrorUtility.createErrorBody(e))
                        Log.e(TAG, "onError:: $error")
                        apiCallback?.onError(error)
                        apiCallback?.onFinish()
                    }

                    override fun onComplete() {
                        apiCallback?.onFinish()
                    }
                })
            )
        }

        fun <T : IEvent> registerRxBus(
            rxBus: RxBus<IEvent>, mCompositeDisposable: CompositeDisposable, clazz: Class<T>,
            consumer: (T) -> Unit
        ) {
        }

        fun disposeComposite(mCompositeDisposable: CompositeDisposable) {
            if (!mCompositeDisposable.isDisposed) {
                mCompositeDisposable.dispose()
            }
        }
    }
}