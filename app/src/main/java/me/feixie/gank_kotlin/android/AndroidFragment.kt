package me.feixie.gank_kotlin.android


import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProvider
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import me.feixie.gank_kotlin.R
import me.feixie.gank_kotlin.api.ContentApiModel
import timber.log.Timber
import kotlinx.android.synthetic.main.fragment_android.view.*
import me.feixie.gank_kotlin.CONTENT_TYPE
import me.feixie.gank_kotlin.common.ViewArticalActivity
import me.feixie.gank_kotlin.util.EndlessRecyclerViewScrollListener


/**
 * A simple [Fragment] subclass.
 */
class AndroidFragment : Fragment() {

    companion object {
        fun newInstance(): AndroidFragment {
            return AndroidFragment()
        }
    }

    private lateinit var mViewModel: AndroidViewModel
    private var mType:String?  = null
    private lateinit var mLayoutManager: LinearLayoutManager

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_android, container, false)
        initData()
        initView(view)
        return view
    }

    private fun initData() {
        mType = arguments?.getString(CONTENT_TYPE)
        mViewModel = ViewModelProviders.of(this).get(AndroidViewModel::class.java)
    }



    private fun initView(view: View) {
        view.rvAndroidContent.hasFixedSize()
        mLayoutManager = LinearLayoutManager(activity)
        view.rvAndroidContent.layoutManager = mLayoutManager
        val listener = object : EndlessRecyclerViewScrollListener(mLayoutManager){
            override fun onLoadMore(page: Int, totalItemsCount: Int, view: RecyclerView) {
                Timber.d("page: "+page)

                //TODO IMPLEMENT LOAD MORE FUNCTION
            }
        }
        if (mType != null) {
            mViewModel.getLiveAndroidContent(mType!!).observe(this, Observer { content ->
                content?.let {
                    view.pbContent.visibility = View.GONE
                    view.rvAndroidContent?.adapter = AndroidAdapter(content, {result ->
                        Timber.d(result.toString())
                        if (result.url.isNotEmpty()) {
                            ViewArticalActivity.startActivity(activity!!, result.url)
                        }
                    })
                    view.rvAndroidContent.addOnScrollListener(listener)
                }
            })
        }

    }

}// Required empty public constructor
