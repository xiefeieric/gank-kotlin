package me.feixie.gank_kotlin.benefit


import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.StaggeredGridLayoutManager
import android.text.Layout
import android.util.LayoutDirection
import android.view.*
import android.view.animation.AlphaAnimation
import android.view.animation.Animation
import kotlinx.android.synthetic.main.fragment_android.view.*
import kotlinx.android.synthetic.main.fragment_benefit.view.*
import me.feixie.gank_kotlin.GankApplication
import me.feixie.gank_kotlin.R
import me.feixie.gank_kotlin.android.AndroidViewModel
import me.feixie.gank_kotlin.api.ContentApiModel
import me.feixie.gank_kotlin.api.Result
import me.feixie.gank_kotlin.common.ViewArticalActivity
import me.feixie.gank_kotlin.util.EndlessRecyclerViewScrollListener
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
    private val mResults = mutableListOf<Result>()


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_benefit, container, false)
        setHasOptionsMenu(true)
        initData()
        initView(view)
        return view
    }


    private fun initData() {
        mViewModel = ViewModelProviders.of(this).get(AndroidViewModel::class.java)
    }

    private lateinit var mLayoutManager: StaggeredGridLayoutManager

    private var mPage = 1

    private fun initView(view: View) {
        mLayoutManager = StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
        view.rvBenefit.layoutManager = mLayoutManager

        val listener = object : EndlessRecyclerViewScrollListener(mLayoutManager) {
            override fun onLoadMore(page: Int, totalItemsCount: Int, view: RecyclerView) {
                mPage += page
                Timber.d("page: " + mPage)

                mViewModel.getLiveAndroidContent("福利", mPage).observe(this@BenefitFragment, Observer { content ->
                    content?.let {

                        content.results.forEach { result ->
                            if (!mResults.map { it._id }.contains(result._id)) {
                                mResults.add(result)
                            }
                        }
                        mLayoutManager = StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
                        view.rvBenefit.layoutManager = mLayoutManager
                        view.rvBenefit.adapter = BenefitRVAdapter(this@BenefitFragment.activity!!, mResults, this@BenefitFragment)
                        mLayoutManager.scrollToPosition((20 * page - 10) / 2)
                    }
                })
            }
        }

        mViewModel.getLiveAndroidContent("福利", mPage).observe(this, Observer { content ->
            content?.let {
                if (mResults.isEmpty()) {
                    mResults += content.results
                }
                if (mResults.isNotEmpty()) {
                    view.pbBenefit.visibility = View.GONE
                }
                if (mPage == 1) {
                    Timber.d("first load")
                    view.rvBenefit.adapter = BenefitRVAdapter(activity!!, mResults, this)
                }
                view.rvBenefit.addOnScrollListener(listener)
            }
        })
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        menu.findItem(R.id.action_search).isVisible = false
    }

    override fun imageOnClick(url: String) {
        Timber.d(url)
        ViewArticalActivity.startActivity(activity!!, url)
    }

}// Required empty public constructor
