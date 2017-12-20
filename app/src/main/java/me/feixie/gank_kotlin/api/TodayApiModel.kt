package me.feixie.gank_kotlin.api

import com.chad.library.adapter.base.entity.SectionEntity
import org.joda.time.DateTime
import timber.log.Timber

/**
 * Created by fei on 12/12/2017.
 */

data class TodayApiModel(
        val category: List<String> = listOf(),
        val error: Boolean = true, //false
        val results: Results = Results()
) {
    fun getDataList(): List<Item> {
        val androidHeader = if (results.Android.isNotEmpty()) listOf(Item(header = true, headerName = results.Android[0].type)) else listOf<Item>()
        val iosHeader = if (results.iOS.isNotEmpty()) listOf(Item(header = true, headerName = results.iOS[0].type)) else listOf<Item>()
        val videoHeader = if (results.休息视频.isNotEmpty()) listOf(Item(header = true, headerName = results.休息视频[0].type)) else listOf<Item>()
        val frontendHeader = if (results.前端.isNotEmpty()) listOf(Item(header = true, headerName = results.前端[0].type)) else listOf<Item>()
        val resourcesHeader = if (results.拓展资源.isNotEmpty()) listOf(Item(header = true, headerName = results.拓展资源[0].type)) else listOf<Item>()

        return androidHeader + results.Android +
                iosHeader + results.iOS +
                videoHeader + results.休息视频 +
                frontendHeader + results.前端 +
                resourcesHeader + results.拓展资源
    }
}

data class Results(
        val Android: List<Item> = listOf(),
        val iOS: List<Item> = listOf(),
        val 休息视频: List<Item> = listOf(),
        val 前端: List<Item> = listOf(),
        val 拓展资源: List<Item> = listOf(),
        val 福利: List<Item> = listOf()
)

data class Item(
        val _id: String = "", //59fefc00421aa90fef2034fd
        val createdAt: String = DateTime().millis.toString(), //2017-11-05T19:54:40.126Z
        val desc: String = "", //别的妹子被撩VS 你被撩，哈哈哈哈我选择胖子！！！！！！[笑cry][笑cry][笑cry] ​
        val publishedAt: String = "", //2017-12-11T08:43:39.682Z
        val source: String = "", //chrome
        val type: String = "", //Video
        val url: String = "", //https://weibo.com/tv/v/FtACAlNjr?fid=1034:af7f2c4cddc6b2d8e373293be47bdc8b&display=0&retcode=6102
        val used: Boolean = false, //true
        val who: String = "", //lxxself
        val header: Boolean = false,
        val headerName: String = ""
)