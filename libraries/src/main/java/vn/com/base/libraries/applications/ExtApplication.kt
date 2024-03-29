package vn.com.base.libraries.applications

import android.app.Application
import vn.com.base.libraries.loggers.Logger
import vn.com.base.libraries.preferences.AppPreferences
import vn.com.base.libraries.utilities.*
import java.io.File

open class ExtApplication : Application() {
    companion object {
        // folder store log, image of application
        private var pathRootProject: File? = null

        // instance of application
        lateinit var instance: ExtApplication

        /**
         * init application
         */
        fun init(application: ExtApplication) {
            instance = application
        }

        /**
         * * @return path root project
         */
        fun getPathProject(): File? {
            OptionalUtility.with(pathRootProject)
                .doIfEmpty { pathRootProject = FileUtility.getRootPath(instance, instance.packageName) }
            return pathRootProject
        }
    }

    override fun onCreate() {
        super.onCreate()

        // set instance application
        init(this)

        // ext utils
        ApplicationUtility.init(this)

        // init instance of app preference
        AppPreferences.init(this)

        // init leak canary
        LeakCanaryUtility.init(this)

        // init notification
        NotificationUtility.init(this)

        // setup path project for logging, image path....
        getPathProject()

        // setup Logger
        Logger.setEnableLog(true)

        // update path to log error
        OptionalUtility.with(getPathProject())
            .doIfPresent { Logger.setPathSaveLog(it.absolutePath, packageName, "log") }
    }
}