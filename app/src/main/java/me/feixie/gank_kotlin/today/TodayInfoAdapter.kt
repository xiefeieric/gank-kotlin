package me.feixie.gank_kotlin.today

import android.graphics.Typeface
import android.widget.LinearLayout
import android.widget.TextView
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseSectionQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import kotlinx.android.synthetic.main.rv_today_item_view.view.*
import me.feixie.gank_kotlin.GankApplication
import me.feixie.gank_kotlin.R
import me.feixie.gank_kotlin.api.Item
import org.jetbrains.anko.textColor

/**
 * Created by fei on 18/12/2017.
 */
class TodayInfoAdapter(private val resource:Int, private val todayData:List<Item>): BaseQuickAdapter<Item, BaseViewHolder>(resource, todayData){

    override fun convert(helper: BaseViewHolder?, item: Item?) {
        if (helper != null && item != null) {
            when (item.header) {
                true -> {
                    helper.setTypeface(R.id.tvTodayTitle, Typeface.SERIF)
                    helper.setTextColor(R.id.tvTodayTitle, GankApplication.instance.resources.getColor(android.R.color.black))
                    helper.setText(R.id.tvTodayTitle, item.headerName)
                }
                else -> {
                    helper.setTextColor(R.id.tvTodayTitle, GankApplication.instance.resources.getColor(R.color.defaultTextColor))
                    helper.setText(R.id.tvTodayTitle, item.desc)
                }
            }

        }
    }

}