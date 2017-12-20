package me.feixie.gank_kotlin.android


import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import me.feixie.gank_kotlin.R


/**
 * A simple [Fragment] subclass.
 */
class AndroidFragment : Fragment() {

    companion object {
        fun newInstance(): AndroidFragment {
            return AndroidFragment()
        }
    }


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_android, container, false)
    }

}// Required empty public constructor
