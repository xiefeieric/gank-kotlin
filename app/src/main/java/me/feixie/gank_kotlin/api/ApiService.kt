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

    fun getToday(): Observable<TodayApiModel> {
        val todayService = GankApplication.mRetrofit.create(GankTodayService::class.java)
        val datetime = DateTime()
//        return todayService.getTodayInfo(datetime.year, datetime.monthOfYear, datetime.dayOfMonth)
        return todayService.getTodayInfo(2017, 12, 11)
    }

}