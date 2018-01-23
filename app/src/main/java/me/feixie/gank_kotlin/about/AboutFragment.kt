package me.feixie.gank_kotlin.about


import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.*

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
        setHasOptionsMenu(true)
        return inflater.inflate(R.layout.fragment_about, container, false)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        menu.findItem(R.id.action_search).isVisible = false
    }

}// Required empty public constructor
