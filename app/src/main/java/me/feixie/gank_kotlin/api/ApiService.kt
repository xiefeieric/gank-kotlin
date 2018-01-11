package me.feixie.gank_kotlin.api

import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import io.reactivex.Observable
import me.feixie.gank_kotlin.GankApplication
import me.feixie.gank_kotlin.dagger.DaggerAppComponent
import org.joda.time.DateTime
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import timber.log.Timber

/**
 * Created by fei on 12/12/2017.
 */
object ApiService {

    fun getToday(year:Int, month:Int, day:Int): Observable<TodayApiModel> {
        val todayService = GankApplication.mRetrofit.create(GankTodayService::class.java)
//        val datetime = DateTime()
        return todayService.getTodayInfo(year, month, day)
//        return todayService.getTodayInfo(2017, 12, 11)
    }

    fun getGankHistory():Observable<HistoryApiModel> {
        val historyService = GankApplication.mRetrofit.create(GankHistoryService::class.java)
        return historyService.getGankHistory()
    }

    fun getAndroidContent(): Observable<ContentApiModel> {
        val androidService = GankApplication.mRetrofit.create(GankAndroidService::class.java)
        return androidService.getAndroidContent()
    }

}