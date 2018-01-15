package me.feixie.gank_kotlin.api

import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Path

/**
 * Created by fei on 12/12/2017.
 */
interface GankTodayService {
    @GET("day/{year}/{month}/{day}")
    fun getTodayInfo(@Path("year") year: Int, @Path("month") month: Int, @Path("day") day: Int): Observable<TodayApiModel>
}

interface GankHistoryService {
    @GET("day/history")
    fun getGankHistory(): Observable<HistoryApiModel>
}

interface GankAndroidService {
    @GET("data/{type}/20/1")
    fun getAndroidContent(@Path("type") type:String) : Observable<ContentApiModel>
}