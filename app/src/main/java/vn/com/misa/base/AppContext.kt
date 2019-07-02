package vn.com.misa.base

import io.reactivex.exceptions.UndeliverableException
import io.reactivex.plugins.RxJavaPlugins
import vn.com.base.libraries.applications.ExtApplication
import vn.com.base.libraries.rxbus.IEvent
import vn.com.base.libraries.rxbus.RxBus
import vn.com.base.libraries.utilities.NetworkUtility
import vn.com.base.libraries.utilities.TypefaceUtility
import vn.com.base.libraries.views.recyclerviews.ExtRecyclerView
import vn.com.misa.base.models.responses.TokenResponse
import vn.com.misa.base.utilities.Constant
import javax.inject.Inject

class AppContext : ExtApplication() {
    companion object {
        private const val TAG = "AppContext"

        var tokenResponse: TokenResponse? = null
            private set

        val language: String? = "vi"

        fun isAuthenticated() = tokenResponse != null

        fun setAuthorized(tokenResponse: TokenResponse, isLogin: Boolean) {
            this.tokenResponse = tokenResponse
            if (isLogin) {
                //save token to sharePreferences
            }
        }


    }

    @Inject
    lateinit var rxBus: RxBus<IEvent>

    override fun onCreate() {
        super.onCreate()

        // paging number per page
        ExtRecyclerView.numberPerPage = Constant.NUMBER_PER_PAGE

//        if(BuildConfig.DEBUG){
//            Logger.setEnableLog(true)
//        }
//
//        if(BuildConfig.IS_CATCH_ALL_EXCEPTION){
//           Thread.setDefaultUncaughtExceptionHandler{
//               thread, e -> Logger.e("$TAG: :${thread.javaClass.name}", e.message)
//           }
//        }
//
//        if(BuildConfig.IS_CONFIG_LEAK_MEMORY){
//            LeakCanaryUtility.init(this)
//        }


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

//        setAuthorized(
//            //getAccesstoken from sharePreferences, false
//        )
    }
}