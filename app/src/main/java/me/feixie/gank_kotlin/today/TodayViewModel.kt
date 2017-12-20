package me.feixie.gank_kotlin.today

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import me.feixie.gank_kotlin.api.HistoryApiModel
import me.feixie.gank_kotlin.api.TodayApiModel
import me.feixie.gank_kotlin.dagger.ApiServiceModule
import me.feixie.gank_kotlin.dagger.DaggerServiceComponent
import org.joda.time.DateTime
import timber.log.Timber

/**
 * Created by fei on 12/12/2017.
 */
class TodayViewModel : ViewModel() {

    private val mDisposable = CompositeDisposable()
    private var mFailListener: ServerError? = null
    private val mServiceComponent = DaggerServiceComponent.builder()
            .apiServiceModule(ApiServiceModule())
            .build()
    private val mApiService = mServiceComponent.injectService()
    private val mTodayData: MutableLiveData<TodayApiModel> = MutableLiveData()
    private val mGankHistory: MutableLiveData<HistoryApiModel> = MutableLiveData()

    fun getLiveTodayInfo(): MutableLiveData<TodayApiModel> {
        mDisposable.add(
                mApiService.getGankHistory()
                        .map { history ->
                            val dateTime = DateTime()
                            var dateReady = dateTime.year.toString() + "-" + dateTime.monthOfYear.toString() + "-" + dateTime.dayOfMonth.toString()
                            var counter = 1
                            while (!history.results.contains(dateReady)) {
                                val tempDate = dateTime.minusDays(counter)
                                dateReady = tempDate.year.toString() + "-" + tempDate.monthOfYear.toString() + "-" + tempDate.dayOfMonth.toString()
                                counter++
                            }
                            return@map dateReady
                        }
                        .flatMap { dateString ->
                            val splitData = dateString.split("-")
                            Timber.d(splitData.toString())
                            return@flatMap mApiService.getToday(splitData[0].toInt(), splitData[1].toInt(), splitData[2].toInt())
                        }
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe({ today ->
                            mTodayData.value = today
                        }, { error ->
                            Timber.d(error.message)
//                            Toasty.error(GankApplication.instance, error.message.toString()).show()
                            mFailListener?.serverDown()
                        })
        )
        return mTodayData
    }

    fun refreshTodayData() {
        getLiveTodayInfo()
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