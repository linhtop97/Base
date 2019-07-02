package vn.com.misa.base.commons.presenters

import vn.com.misa.base.commons.views.IBaseMvpView

interface IBaseMvpPresenter<V : IBaseMvpView> {
    fun attachView(view: V)

    fun getView(consumer: (v: V) -> Unit)

    fun isViewAttached(): Boolean

    fun detachView()
}