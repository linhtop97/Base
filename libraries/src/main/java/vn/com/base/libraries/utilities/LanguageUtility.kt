package vn.com.base.libraries.utilities

import android.app.Activity
import vn.com.base.libraries.loggers.Logger
import java.util.*

@Suppress("DEPRECATION")
class LanguageUtility {
    companion object {
        private const val TAG = "LanguageUtility"

        fun configLanguage(activity: Activity, language: String?) {
            try {
                StringUtility.with(language).doIfPresent {
                    val res = activity.resources
                    val config = res.configuration
                    config.locale = Locale(it)
                    res.updateConfiguration(config, res.displayMetrics)
                }
            } catch (e: Exception) {
                Logger.e(TAG, e)
            }
        }
    }
}