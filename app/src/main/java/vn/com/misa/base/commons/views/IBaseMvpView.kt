package vn.com.misa.base.commons.views

import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import vn.com.base.libraries.rxbus.IEvent
import vn.com.base.libraries.rxbus.RxBus
import vn.com.misa.base.AppContext

interface IBaseMvpView {

    var appContext: AppContext

    var rxBus: RxBus<IEvent>

    fun getCompositeDisposable(): CompositeDisposable

    fun isNetworkAvailable(): Boolean

    fun showDialogLoading(isLoading: Boolean)

    fun showMessage(message: String?)

    fun showMessage(message: String?, ok: String)

    fun showMessage(message: String?, ok: String, consumer: () -> Unit)

    fun showMessage(message: String?, ok: String, cancel: String, okConsumer: () -> Unit, cancelConsumer: () -> Unit)

    fun showLoadingInPagingView(isLoading: Boolean)

    fun showLoadingInView(isLoading: Boolean)

    fun <T : IEvent> registerRxBus(clazz: Class<T>, consumer: (T) -> Unit)

    fun subscribeWith(consumer: () -> Disposable)
}