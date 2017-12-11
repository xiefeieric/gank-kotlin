package me.feixie.gank_kotlin.today


import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import me.feixie.gank_kotlin.R


/**
 * A simple [Fragment] subclass.
 */
class TodayFragment : Fragment() {


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_today, container, false)
        return view
    }

}// Required empty public constructor
