package vn.com.base.libraries.utilities

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.Window
import android.view.WindowManager

/**
 * Lớp tiện ích xử lý điều hướng các thao tác với activity
 * @Created_by nblinh on 07/06/2019
 */
class ActivityUtility {
    companion object {
        private const val TAG = "ActivityUtility"

        /**
         * Phương thức kiểm tra activity còn hoạt động hay không
         * @Created_by nblinh on 08/06/2019
         */
        fun isFinish(activity: Activity?): Boolean = activity == null || activity.isFinishing

        /**
         * Phương thức khởi chạy 1 activity mới
         * @param context ngữ cảnh nơi start activity
         * @param clazz lớp activity muốn khởi chạy
         * @param bundle gói dữ liệu được truyền sang clazz
         * @Created_by nblinh on 08/06/2019
         */
        fun startActivity(context: Context, clazz: Class<*>, bundle: Bundle? = null) {
            val intent = Intent(context, clazz)
            bundle?.let { intent.putExtras(bundle) }
            context.startActivity(intent)
        }

        fun startActivityForResult() {

        }

        /**
         * Phương thức thiết lập toàn màn hình
         * @Created_by nblinh on 08/06/2019
         */
        fun setFullScreen(activity: Activity) {
            activity.requestWindowFeature(Window.FEATURE_NO_TITLE)
            activity.window.setFlags(
                WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN
            )
        }

    }
}