package vn.com.base.libraries.views.recyclerviews

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import butterknife.ButterKnife

open class ExtRecyclerViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    init {
        ButterKnife.bind(itemView)
    }
}