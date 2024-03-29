package vn.com.base.libraries.views.scrollviews

import android.content.Context
import android.util.AttributeSet
import android.widget.HorizontalScrollView

class ExtHorizontalScrollView : HorizontalScrollView {
    var scrollChangedConsumer: ((scrollX: Int, scrollY: Int, oldScrollX: Int, oldScrollY: Int) -> Unit)? = null

    constructor(context: Context) : super(context)

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs)

    constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int) : super(context, attrs, defStyleAttr)

    override fun onScrollChanged(x: Int, y: Int, oldX: Int, oldY: Int) {
        super.onScrollChanged(x, y, oldX, oldY)
        scrollChangedConsumer?.invoke(x, y, oldX, oldY)
    }
}
