package me.feixie.gank_kotlin.today

import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import kotlinx.android.synthetic.main.rv_today_item_view.view.*
import me.feixie.gank_kotlin.R
import me.feixie.gank_kotlin.api.Item

/**
 * Created by fei on 18/12/2017.
 */
class TodayInfoAdapter(private val resource:Int, private val todayData:List<Item>): BaseQuickAdapter<Item, BaseViewHolder>(resource, todayData){

    override fun convert(helper: BaseViewHolder?, item: Item?) {
        if (helper != null && item != null) {
            helper.setText(R.id.tvTodayTitle, item.desc)
        }
    }

}