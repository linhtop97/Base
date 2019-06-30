package vn.com.base.libraries.utilities

import android.graphics.Color

class ColorUtility {
    companion object {
        fun getColorWithAlpha(color: Int, ratio: Float): Int {
            val r = Color.red(color)
            val g = Color.green(color)
            val b = Color.blue(color)
            return Color.argb(Math.round(Color.alpha(color) * (if (ratio > 1) 1f else ratio)), r, g, b)
        }
    }
}