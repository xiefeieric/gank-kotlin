package me.feixie.gank_kotlin.benefit


import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.StaggeredGridLayoutManager
import android.text.Layout
import android.util.LayoutDirection
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.fragment_benefit.view.*
import me.feixie.gank_kotlin.R
import me.feixie.gank_kotlin.android.AndroidViewModel
import me.feixie.gank_kotlin.api.ContentApiModel
import me.feixie.gank_kotlin.common.ViewArticalActivity
import timber.log.Timber


/**
 * A simple [Fragment] subclass.
 */
class BenefitFragment : Fragment(), ImageClickListener {

    companion object {
        fun newInstance(): BenefitFragment {
            return BenefitFragment()
        }
    }

    private lateinit var mViewModel: AndroidViewModel


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_benefit, container, false)
        initData()
        initView(view)
        return view
    }



    private fun initData() {
        mViewModel = ViewModelProviders.of(this).get(AndroidViewModel::class.java)
    }

    private fun initView(view: View) {
        view.rvBenefit.setHasFixedSize(true)
        view.rvBenefit.layoutManager = StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
        mViewModel.getLiveAndroidContent("福利").observe(this, Observer { content ->
            content?.let {
                view.rvBenefit.adapter = BenefitRVAdapter(activity!!, content, this)
            }
        })
    }

    override fun imageOnClick(url: String) {
        Timber.d(url)
        ViewArticalActivity.startActivity(activity!!, url)
    }

}// Required empty public constructor
