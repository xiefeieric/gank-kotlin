package me.feixie.gank_kotlin.android

import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.rv_android_item_view.view.*
import me.feixie.gank_kotlin.GankApplication
import me.feixie.gank_kotlin.R
import me.feixie.gank_kotlin.api.ContentApiModel
import me.feixie.gank_kotlin.api.Result

/**
 * Created by fei on 11/01/2018.
 */
class AndroidAdapter(private val content:ContentApiModel, val listener:(Result)->Unit): RecyclerView.Adapter<ItemViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val view = View.inflate(GankApplication.instance, R.layout.rv_android_item_view, null)
        return ItemViewHolder(view)
    }

    override fun getItemCount(): Int {
        return content.results.size
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        content.results[position].apply {
            holder.itemView.tvContentTitle.text = desc
            holder.itemView.tvContentTitle.setOnClickListener { listener(this) }
        }
    }
}

class ItemViewHolder(itemView:View):RecyclerView.ViewHolder(itemView)