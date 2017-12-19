package me.feixie.gank_kotlin.today

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import es.dmoral.toasty.Toasty
import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import me.feixie.gank_kotlin.GankApplication
import me.feixie.gank_kotlin.api.ApiService
import me.feixie.gank_kotlin.api.TodayApiModel
import me.feixie.gank_kotlin.dagger.ApiServiceModule
import me.feixie.gank_kotlin.dagger.DaggerServiceComponent
import me.feixie.gank_kotlin.dagger.ServiceComponent
import timber.log.Timber
import java.util.*

/**
 * Created by fei on 12/12/2017.
 */
class TodayViewModel:ViewModel() {

    private val mDisposable = CompositeDisposable()
    private var mFailListener:ServerError? = null
    private val mServiceComponent = DaggerServiceComponent.builder()
            .apiServiceModule(ApiServiceModule())
            .build()
    private val mApiService = mServiceComponent.injectService()
    private val mTodayData:MutableLiveData<TodayApiModel> = MutableLiveData()

    fun getLiveTodayInfo(): MutableLiveData<TodayApiModel> {
        mDisposable.add(
                mApiService.getToday()
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe({today ->
                            mTodayData.value = today
                        }, {error ->
                            Timber.d(error.message)
                            Toasty.error(GankApplication.instance, error.message.toString()).show()
                            mFailListener?.serverDown()
                        })
        )
        return mTodayData
    }

    fun setFailListener(listener: ServerError) {
        mFailListener = listener
    }

    override fun onCleared() {
        super.onCleared()
        mDisposable.clear()
    }
}

interface ServerError {
    fun serverDown()
}