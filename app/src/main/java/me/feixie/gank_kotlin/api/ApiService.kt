package me.feixie.gank_kotlin.api

import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import io.reactivex.Observable
import org.joda.time.DateTime
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import timber.log.Timber

/**
 * Created by fei on 12/12/2017.
 */
object ApiService {

    private var retrofit:Retrofit? = null

    private fun initRetrofit():Retrofit {
        if (retrofit == null) {
            retrofit = Retrofit.Builder()
                    .baseUrl("https://gank.io/api/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .build()
        }
        return retrofit!!
    }

    fun getToday(): Observable<TodayApiModel> {
        val todayService = initRetrofit().create(GankTodayService::class.java)
        val datetime = DateTime()
//        return todayService.getTodayInfo(datetime.year, datetime.monthOfYear, datetime.dayOfMonth)
        return todayService.getTodayInfo(2017, 12, 11)
    }

}