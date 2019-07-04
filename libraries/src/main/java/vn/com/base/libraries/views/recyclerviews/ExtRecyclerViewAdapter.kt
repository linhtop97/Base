package vn.com.base.libraries.views.recyclerviews

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import vn.com.base.libraries.utilities.CollectionUtility

abstract class ExtRecyclerViewAdapter<T, VH : ExtRecyclerViewHolder>(ctx: Context, its: ArrayList<T?>? = null) :
    RecyclerView.Adapter<VH>() {

    protected var context: Context = ctx
    private var layoutInflater: LayoutInflater = LayoutInflater.from(ctx)
    protected var items: ArrayList<T?>? = its

    private var loadingId: Int = 0

    /**
     * @param viewType
     * @return
     */
    protected abstract fun getLayoutViewId(viewType: Int): Int

    /**
     * @param view
     * @param viewType
     * @return
     */
    protected abstract fun onCreateHolder(view: View, viewType: Int): VH


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH =
        onCreateHolder(layoutInflater.inflate(getLayoutViewId(viewType), parent, false), viewType)

    private fun getViewHolder(viewType: Int) {

    }

    override fun getItemCount(): Int = CollectionUtility.with(items).size()

    /**
     * @param loadingId
     */
    fun setLoadingLayout(loadingId: Int): ExtRecyclerViewAdapter<T, VH> {
        this.loadingId = loadingId
        return this
    }

    /**
     * @param its
     */
    fun setItems(its: ArrayList<T?>?): ExtRecyclerViewAdapter<T, VH> {
        if (CollectionUtility.isNullOrEmpty(its)) return this
        CollectionUtility.with(items).doIfEmpty { items = ArrayList() }

        items!!.clear()
        items!!.addAll(its!!)
        notifyDataSetChanged()
        return this
    }

    /**
     * @param position
     * @return
     */
    fun getItem(position: Int): T? = if (CollectionUtility.isNullOrEmpty(items)) null else items!![position]

    /**
     * @param item
     */
    fun addItem(item: T?): ExtRecyclerViewAdapter<T, VH> {
        CollectionUtility.with(items).doIfEmpty { items = ArrayList() }

        items!!.add(item)
        notifyItemInserted(itemCount)
        return this
    }

    /**
     * @param index
     * @param item
     */
    fun addItem(index: Int, item: T?): ExtRecyclerViewAdapter<T, VH> {
        CollectionUtility.with(items).doIfEmpty { items = ArrayList() }

        items!!.add(index, item)
        notifyItemInserted(index)
        return this
    }

    /**
     * @param its
     */
    fun addItems(its: ArrayList<T?>?): ExtRecyclerViewAdapter<T, VH> {
        if (CollectionUtility.isNullOrEmpty(its)) return this
        CollectionUtility.with(items).doIfEmpty { items = ArrayList() }

        items!!.addAll(its!!)
        notifyItemRangeInserted(itemCount, its.size)
        return this
    }

    /**
     * @param index
     * @param item
     */
    fun update(index: Int, item: T?): ExtRecyclerViewAdapter<T, VH> {
        if (CollectionUtility.isNullOrEmpty(items)) return this

        items!![index] = item
        notifyItemChanged(index)
        return this
    }

    /**
     * @param item
     */
    fun removeItem(item: T?): ExtRecyclerViewAdapter<T, VH> {
        if (CollectionUtility.isNullOrEmpty(items)) return this

        removeItem(items!!.indexOf(item))
        return this
    }

    /**
     * @param index
     */
    fun removeItem(index: Int): ExtRecyclerViewAdapter<T, VH> {
        if (CollectionUtility.isNullOrEmpty(items) || index <= -1) return this

        items!!.removeAt(index)
        notifyItemRemoved(index)
        return this
    }

    /**
     * clear data
     */
    fun clear(): ExtRecyclerViewAdapter<T, VH> {
        if (CollectionUtility.isNullOrEmpty(items)) return this
        items!!.clear()
        notifyDataSetChanged()
        return this
    }

    /**
     * is loading view
     *
     * @return
     */
    val isLoading: Boolean
        get() {
            if (CollectionUtility.isNullOrEmpty(items)) return false
            return items!!.contains(null)
        }

    /**
     * show loading
     */
    fun showLoading(): ExtRecyclerViewAdapter<T, VH> {
        CollectionUtility.with(items).doIfEmpty { items = ArrayList() }
        if (!items!!.contains(null)) addItem(null)
        return this
    }

    /**
     * hide loading
     */
    fun hideLoading(): ExtRecyclerViewAdapter<T, VH> {
        removeItem(null)
        return this
    }

    /**
     * loading holder
     */
    public class LoadingHolder(itemView: View) : ExtRecyclerViewHolder(itemView)
}



