package me.feixie.gank_kotlin.today


import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import me.feixie.gank_kotlin.R
import timber.log.Timber


/**
 * A simple [Fragment] subclass.
 */
class TodayFragment : Fragment() {

    private lateinit var mViewModel: TodayViewModel

    companion object {
        fun instance(): TodayFragment {
            return TodayFragment()
        }
    }


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        mViewModel = ViewModelProviders.of(this).get(TodayViewModel::class.java)
        mViewModel.getLiveTodayInfo().observe(this, Observer { today ->
            Timber.d(today.toString())
        })
        val view = inflater.inflate(R.layout.fragment_today, container, false)
        return view
    }

}// Required empty public constructor
