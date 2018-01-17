package me.feixie.gank_kotlin.about


import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import me.feixie.gank_kotlin.R


/**
 * A simple [Fragment] subclass.
 */
class AboutFragment : Fragment() {

    companion object {
        fun newInstance(): AboutFragment {
            return AboutFragment()
        }
    }


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_about, container, false)
    }

}// Required empty public constructor
