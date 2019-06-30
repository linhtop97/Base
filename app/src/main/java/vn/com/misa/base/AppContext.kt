package vn.com.misa.base

import io.reactivex.exceptions.UndeliverableException
import vn.com.base.libraries.preferences.AppPreferences
import vn.com.base.libraries.rxbus.IEvent
import vn.com.base.libraries.utilities.GsonUtility
import vn.com.base.libraries.utilities.NetworkUtility
import vn.com.base.libraries.utilities.TypefaceUtility
import vn.com.base.libraries.views.recyclerviews.ExtRecyclerView
import vn.com.misa.base.utilities.Constant

import io.reactivex.plugins.RxJavaPlugins
import vn.com.base.libraries.applications.ExtApplication
import vn.com.base.libraries.rxbus.RxBus
import javax.inject.Inject

class AppContext : ExtApplication() {
    companion object {
        private const val TAG = "AppContext"
    }

    @Inject
    lateinit var rxBus: RxBus<IEvent>

    override fun onCreate() {
        super.onCreate()

        // paging number per page
        ExtRecyclerView.numberPerPage = Constant.NUMBER_PER_PAGE

        // https://github.com/Polidea/RxAndroidBle/wiki/FAQ:-UndeliverableException
        RxJavaPlugins.setErrorHandler { error ->
            if (error is UndeliverableException) {
                return@setErrorHandler // ignore BleExceptions as they were surely delivered at least once
            }
            // add other custom handlers if needed
            throw error
        }

        // set font default
        TypefaceUtility.FONT_DEFAULT = getString(R.string.font_normal_display)

        // overwrite all font
        TypefaceUtility.overrideFont(this, "SERIF", getString(R.string.font_normal_display))

        // trust all certificate
        NetworkUtility.nuke()
    }
}