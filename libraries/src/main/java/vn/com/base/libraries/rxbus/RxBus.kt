package vn.com.base.libraries.rxbus

import android.os.Handler
import android.os.Looper
import rx.Observable
import rx.subjects.PublishSubject

/**
 * Dùng để gửi và nhận các thông tin/tín hiệu
 * @Created_by nblinh on 07/06/2019
 */
class RxBus<T : IEvent> {
    companion object {
        private const val TAG = "RxBus"

        //khởi tạo instance của RxBus
        val instance: RxBus<IEvent> = RxBus()
    }

    //publish Subject rxjava
    private val mPublishSubject = PublishSubject.create<T>()

    //handler data
    private val handler = Handler(Looper.getMainLooper())

    /**
     * Hàm xác định kiểu Event lắng nghe kết quả
     * @param clazz của lớp đối tượng muốn lăng nghe
     * @return trả về lớp lắng nghe tín hiệu
     * @Created_by nblinh on 07/06/2019
     */
    fun <A : IEvent> ofType(clazz: Class<A>): Observable<A>? = mPublishSubject.ofType(clazz)

    /**
     * Hàm dùng để bắn tín hiệu đi
     * @param event là thông tin tín hiệu muốn gửi đi
     * @param onFinish là 1 callback dùng để thông báo cho biết đã gửi xong
     * @Created_by nblinh on 07/06/2019
     */
    fun send(event: T, onFinish: (() -> Unit)? = null) = handler.post {
        mPublishSubject.onNext(event)
        onFinish?.invoke()
    }
}