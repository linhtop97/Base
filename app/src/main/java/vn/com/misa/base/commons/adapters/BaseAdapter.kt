package vn.com.misa.base.commons.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import vn.com.base.libraries.utilities.CollectionUtility
import vn.com.base.libraries.views.recyclerviews.ExtRecyclerViewHolder

abstract class BaseAdapter<T, VH : ExtRecyclerViewHolder>
    (
    val context: Context,
    var items: ArrayList<T>?
) : RecyclerView.Adapter<VH>() {

    val layoutInflater = LayoutInflater.from(context)


    abstract fun getLayoutViewId(viewType: Int): Int

    fun getItem(position: Int): T? = items?.get(position)


    /**
     * Phương thức tạo viewHolder
     * @param
     * @Created_by nblinh on 04/07/2019
     */
    protected abstract fun onCreateHolder(view: View, viewType: Int): VH

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH =
        onCreateHolder(layoutInflater.inflate(getLayoutViewId(viewType), parent, false), viewType)

    override fun getItemCount(): Int = CollectionUtility.with(items).size()

    /**
     * Phương thức cập nhật lại toàn bộ danh sách
     * @param
     * @Created_by nblinh on 04/07/2019
     */
    fun notifyDataSetChanged(items: ArrayList<T>?) {
        this.items = items
        this.notifyDataSetChanged()
    }
}