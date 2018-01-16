package me.feixie.gank_kotlin.benefit

import android.app.Activity
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.fragment_benefit.view.*
import kotlinx.android.synthetic.main.rv_benefit_item_view.view.*
import me.feixie.gank_kotlin.GankApplication
import me.feixie.gank_kotlin.R
import me.feixie.gank_kotlin.api.ContentApiModel
import org.jetbrains.anko.appcompat.v7._ListMenuItemView

/**
 * Created by fei on 16/01/2018.
 */
class BenefitRVAdapter(private val mContext:Activity, private val mContent:ContentApiModel, private val mListener: ImageClickListener):RecyclerView.Adapter<BenefitViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BenefitViewHolder {
        val view = View.inflate(GankApplication.instance, R.layout.rv_benefit_item_view, null)
        view.setOnClickListener {

        }
        return BenefitViewHolder(view)
    }

    override fun getItemCount(): Int {
        return mContent.results.size
    }

    override fun onBindViewHolder(holder: BenefitViewHolder, position: Int) {
        val model = mContent.results[position]
        Glide.with(mContext).load(model.url).into(holder.ivBenefit)
        holder.ivBenefit.setOnClickListener {
            mListener.imageOnClick(model.url)
        }
    }

}

class BenefitViewHolder(itemView: View):RecyclerView.ViewHolder(itemView) {
    val ivBenefit: ImageView = itemView.ivBenefit
}

interface ImageClickListener {
    fun imageOnClick(url:String)
}