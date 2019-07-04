package vn.com.misa.base.commons.adapters

import android.content.Context
import android.view.View
import vn.com.base.libraries.views.recyclerviews.ExtRecyclerViewAdapter
import vn.com.base.libraries.views.recyclerviews.ExtRecyclerViewHolder
import vn.com.misa.base.R
import vn.com.misa.base.models.enums.OptionEnum
import vn.com.misa.base.models.models.OptionModel

class OptionAdapter<VH : ExtRecyclerViewHolder>(
    context: Context,
    items: ArrayList<OptionModel?>?,
    val selectedConsumer: (OptionModel) -> Unit
) :
    ExtRecyclerViewAdapter<OptionModel, VH>(context, items) {

    /**
     * Phương thức lấy content viewId cho item
     * @param
     * @Created_by nblinh on 04/07/2019
     */
    override fun getLayoutViewId(viewType: Int): Int {
        return when (viewType) {
            OptionEnum.OPTION_HEADER.code -> R.layout.item_option
            OptionEnum.OPTION_ITEM.code -> R.layout.item_option
            OptionEnum.OPTION_LINE.code -> R.layout.item_option
            else -> R.layout.item_loading
        }
    }

    override fun getItemViewType(position: Int): Int {
        return when (items!![position]?.viewType) {
            OptionEnum.OPTION_HEADER -> OptionEnum.OPTION_HEADER.code
            OptionEnum.OPTION_ITEM -> OptionEnum.OPTION_ITEM.code
            OptionEnum.OPTION_LINE -> OptionEnum.OPTION_LINE.code
            else -> OptionEnum.OPTION_NULL.code
        }
    }

    @Suppress("UNCHECKED_CAST")
    override fun onCreateHolder(view: View, viewType: Int): VH {
        return when (viewType) {
            OptionEnum.OPTION_HEADER.code -> HeaderHolder(view) as VH
            OptionEnum.OPTION_ITEM.code -> HeaderHolder(view) as VH
            OptionEnum.OPTION_LINE.code -> HeaderHolder(view) as VH
            else -> LoadingHolder(view) as VH
        }
    }

    override fun onBindViewHolder(holder: VH, position: Int) {
        val optionModel = getItem(position) ?: return

        when (holder) {
            is HeaderHolder -> holder.bindData(optionModel)
        }

        holder.itemView.setOnClickListener {
            selectedConsumer(optionModel)
        }
    }

    class HeaderHolder(itemView: View) : ExtRecyclerViewHolder(itemView) {
//        @BindView(R.id.tvSomething)
//        lateinit var tvSomeThing:ExtTextView

        fun bindData(optionModel: OptionModel?) {
            optionModel?.let {

            }
        }
    }
}