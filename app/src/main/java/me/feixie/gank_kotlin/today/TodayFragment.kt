package me.feixie.gank_kotlin.today


import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import kotlinx.android.synthetic.main.fragment_today.view.*

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
        initData(view)
        return view
    }

    private fun initData(view: View) {
        mViewModel.getLiveTodayInfo().observe(this, Observer { today ->
            today?.let {
                Timber.d(today.toString())
                view.rvTodayInfo.adapter = TodayInfoAdapter(R.layout.rv_today_item_view, today.getDataList())
            }
        })
    }

}// Required empty public constructor
