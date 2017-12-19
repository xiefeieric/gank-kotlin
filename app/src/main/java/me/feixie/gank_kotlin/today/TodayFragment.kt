package me.feixie.gank_kotlin.today


import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.graphics.drawable.Drawable
import android.opengl.Visibility
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target
import com.oushangfeng.pinnedsectionitemdecoration.PinnedHeaderItemDecoration
import es.dmoral.toasty.Toasty
import kotlinx.android.synthetic.main.fragment_today.view.*
import kotlinx.android.synthetic.main.item_header_today.view.*
import me.feixie.gank_kotlin.GankApplication

import me.feixie.gank_kotlin.R
import me.feixie.gank_kotlin.api.Item
import me.feixie.gank_kotlin.api.TodayApiModel
import timber.log.Timber


/**
 * A simple [Fragment] subclass.
 */
class TodayFragment : Fragment() {

    private lateinit var mViewModel: TodayViewModel

    companion object {
        fun newInstance(): TodayFragment {
            return TodayFragment()
        }
    }


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        mViewModel = ViewModelProviders.of(this).get(TodayViewModel::class.java)
        val view = inflater.inflate(R.layout.fragment_today, container, false)
        view.rvTodayInfo.layoutManager = LinearLayoutManager(activity)
        view.rvTodayInfo.setHasFixedSize(true)
        initListeners(view)
        initData(view)
        return view
    }

    private fun initListeners(view:View) {
        mViewModel.setFailListener(object:ServerError{
            override fun serverDown() {
                hideLoading(view)
            }
        } )
    }

    private fun initData(view: View) {
        mViewModel.getLiveTodayInfo().observe(this, Observer { today ->
            today?.let {
                Timber.d(today.toString())
                val adapter = TodayInfoAdapter(R.layout.rv_today_item_view,  today.getDataList())
                val header = View.inflate(activity, R.layout.item_header_today, null)
                Glide.with(this)
                        .load(today.results.福利[0].url)
                        .listener(object : RequestListener<Drawable>{
                            override fun onLoadFailed(e: GlideException?, model: Any?, target: Target<Drawable>?, isFirstResource: Boolean): Boolean {
                                header.pbTodayHeaderLoading.visibility = View.GONE
                                Toasty.warning(GankApplication.instance, "Image not ready!").show()
                                return false
                            }

                            override fun onResourceReady(resource: Drawable?, model: Any?, target: Target<Drawable>?, dataSource: DataSource?, isFirstResource: Boolean): Boolean {
                                header.pbTodayHeaderLoading.visibility = View.GONE
                                return false
                            }
                        })
                        .into(header.ivGirl)
                adapter.addHeaderView(header)
                view.rvTodayInfo.adapter = adapter
                hideLoading(view)
            }
        })
    }

    private fun hideLoading(view: View) {
        view.pbTodayLoading.visibility = View.GONE
    }

}// Required empty public constructor
