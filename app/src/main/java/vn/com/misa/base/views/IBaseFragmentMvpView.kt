package vn.com.misa.base.views

import android.view.View
import androidx.annotation.LayoutRes
import androidx.appcompat.app.ActionBar

interface IBaseFragmentMvpView : IBaseMvpView {
    val titleId: Int

    val titleString: String

    @get:LayoutRes
    val resourceId: Int

    fun bindButterKnife(view: View)

    fun initToolbar(supportActionBar: ActionBar)

    fun showToastError(message: String?, dismissConsumer: (() -> Unit)? = null)

    fun showToastInfo(message: String?, dismissConsumer: (() -> Unit)? = null)

    fun showToastSuccess(message: String?, dismissConsumer: (() -> Unit)? = null)

    fun showToastWarm(message: String?, dismissConsumer: (() -> Unit)? = null)
}