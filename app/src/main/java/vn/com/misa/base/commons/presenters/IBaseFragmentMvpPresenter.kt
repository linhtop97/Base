package vn.com.misa.base.commons.presenters

import android.os.Bundle
import vn.com.misa.base.commons.views.IBaseFragmentMvpView

interface IBaseFragmentMvpPresenter<V : IBaseFragmentMvpView> : IBaseMvpPresenter<V> {
    fun processArguments(arguments: Bundle?)

    fun saveInstanceState(bundle: Bundle?)

    fun restoreInstanceState(bundle: Bundle?)
}