package vn.com.misa.base.listeners

abstract class ApiCallback<T> : IApiCallback<T> {
    override fun onStart() {}

    override fun onFinish() {}
}
