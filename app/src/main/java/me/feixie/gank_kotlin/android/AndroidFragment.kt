package me.feixie.gank_kotlin.android


import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.view.MenuItemCompat
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.SearchView
import android.view.*
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import io.reactivex.subjects.PublishSubject
import kotlinx.android.synthetic.main.fragment_android.view.*
import me.feixie.gank_kotlin.CONTENT_TYPE
import me.feixie.gank_kotlin.R
import me.feixie.gank_kotlin.api.Result
import me.feixie.gank_kotlin.common.ViewArticalActivity
import me.feixie.gank_kotlin.util.EndlessRecyclerViewScrollListener
import timber.log.Timber
import java.util.concurrent.TimeUnit


/**
 * A simple [Fragment] subclass.
 */
class AndroidFragment : Fragment(), SearchView.OnQueryTextListener {

    companion object {
        fun newInstance(): AndroidFragment {
            return AndroidFragment()
        }
    }

    private lateinit var mViewModel: AndroidViewModel
    private var mType: String? = null
    private lateinit var mLayoutManager: LinearLayoutManager
    private val mResults = mutableListOf<Result>()
    private var mPage = 1
    private val mSubject = PublishSubject.create<String>()
    private val mDisposable = CompositeDisposable()
    private var mQuery = ""

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {
        // Inflate the layout for this fragment
        setHasOptionsMenu(true)
        val view = inflater.inflate(R.layout.fragment_android, container, false)
        initData()
        initView(view)
        return view
    }

    override fun onDestroyView() {
        super.onDestroyView()
        mDisposable.clear()
    }

    private fun initData() {
        mType = arguments?.getString(CONTENT_TYPE)
        mViewModel = ViewModelProviders.of(this).get(AndroidViewModel::class.java)
    }


    private fun initView(view: View) {
        view.rvAndroidContent.hasFixedSize()
        mLayoutManager = LinearLayoutManager(activity)
        view.rvAndroidContent.layoutManager = mLayoutManager
        val listener = object : EndlessRecyclerViewScrollListener(mLayoutManager) {
            override fun onLoadMore(page: Int, totalItemsCount: Int, view: RecyclerView) {
                if (mQuery.isEmpty()) {
                    Timber.d("page: " + page)
                    mPage += page
                }

                //TODO IMPLEMENT LOAD MORE FUNCTION
                mViewModel.getLiveAndroidContent(mType!!, mPage).observe(this@AndroidFragment, Observer { content ->
                    content?.let {

                        content.results.forEach { result ->
                            if (!mResults.map { it._id }.contains(result._id)) {
                                mResults.add(result)
                            }
                        }
                        mLayoutManager.scrollToPosition(20 * page - 15)
                    }
                })
            }
        }
        if (mType != null) {
            mViewModel.getLiveAndroidContent(mType!!, mPage).observe(this, Observer { content ->
                content?.let {
                    if (mResults.isEmpty()) {
                        Timber.d("hello")
                        mResults += content.results
                    }
                    view.pbContent.visibility = View.GONE
                    if (mQuery.isEmpty()) {
                        view.rvAndroidContent.adapter = AndroidAdapter(mResults, { result ->
                            if (result.url.isNotEmpty()) {
                                ViewArticalActivity.startActivity(activity!!, result.url)
                            }
                        })
                    }
                    view.rvAndroidContent.addOnScrollListener(listener)
                }
            })
        }

        //query stream
        mDisposable.add(
                mSubject.debounce(300, TimeUnit.MILLISECONDS)
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe({ text ->
                            mQuery = text
                            Timber.d("sub: " + text)
                            val filteredList = mResults.filter { result -> result.desc.contains(text, true) }
                            view.rvAndroidContent.adapter = AndroidAdapter(filteredList, { result ->
                                if (result.url.isNotEmpty()) {
                                    ViewArticalActivity.startActivity(activity!!, result.url)
                                }
                            })
                        })
        )


    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        val searchItem = menu.findItem(R.id.action_search)
        val searchView = MenuItemCompat.getActionView(searchItem) as SearchView
        searchView.setOnQueryTextListener(this)

    }

    override fun onQueryTextSubmit(query: String): Boolean {
        return false
    }

    override fun onQueryTextChange(newText: String): Boolean {
        mSubject.onNext(newText)
        return false
    }

}
