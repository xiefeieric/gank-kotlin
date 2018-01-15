package me.feixie.gank_kotlin.benefit


import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import me.feixie.gank_kotlin.R


/**
 * A simple [Fragment] subclass.
 */
class BenefitFragment : Fragment() {

    companion object {
        fun newInstance(): BenefitFragment {
            return BenefitFragment()
        }
    }


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_benefit, container, false)
    }

}// Required empty public constructor
