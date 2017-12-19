package me.feixie.gank_kotlin.api

import com.chad.library.adapter.base.entity.SectionEntity
import org.joda.time.DateTime

/**
 * Created by fei on 12/12/2017.
 */

data class TodayApiModel(
        val category: List<String> = listOf(),
        val error: Boolean = true, //false
        val results: Results = Results()
) {
    fun getDataList(): List<Item> {
        return listOf(Item(header = true, headerName = results.Android[0].type)) + results.Android + listOf(Item(header = true, headerName = results.iOS[0].type)) +
                results.iOS + listOf(Item(header = true, headerName = results.休息视频[0].type)) + results.休息视频 + listOf(Item(header = true, headerName = results.前端[0].type)) +
                results.前端 + listOf(Item(header = true, headerName = results.拓展资源[0].type)) + results.拓展资源
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

//data class Video(
//        val _id: String = "", //59fefc00421aa90fef2034fd
//        val createdAt: String = DateTime().millis.toString(), //2017-11-05T19:54:40.126Z
//        val desc: String = "", //别的妹子被撩VS 你被撩，哈哈哈哈我选择胖子！！！！！！[笑cry][笑cry][笑cry] ​
//        val publishedAt: String = "", //2017-12-11T08:43:39.682Z
//        val source: String = "", //chrome
//        val type: String = "", //Video
//        val url: String = "", //https://weibo.com/tv/v/FtACAlNjr?fid=1034:af7f2c4cddc6b2d8e373293be47bdc8b&display=0&retcode=6102
//        val used: Boolean = false, //true
//        val who: String = "" //lxxself
//)
//
//data class FrontEnd(
//        val _id: String = "", //5a27faaf421aa90fef20358a
//        val createdAt: String = "", //2017-12-06T22:11:59.321Z
//        val desc: String = "", //一只技术amazui实现的vue.js组件库
//        val publishedAt: String = "", //2017-12-11T08:43:39.682Z
//        val source: String = "", //web
//        val type: String = "", //FrontEnd
//        val url: String = "", //https://github.com/sunshineJi/amaze-vue
//        val used: Boolean = false, //true
//        val who: String = "" //Xuecong
//)
//
//data class IOS(
//        val _id: String = "", //5a2dd246421aa90fe50c026f
//        val createdAt: String = "", //2017-12-11T08:33:10.107Z
//        val desc: String = "", //Powermode 输入效果，shaking，shaking！
//        val images: List<String> = listOf(),
//        val publishedAt: String = "", //2017-12-11T08:43:39.682Z
//        val source: String = "", //chrome
//        val type: String = "", //iOS
//        val url: String = "", //https://github.com/younatics/PowerMode
//        val used: Boolean = false, //true
//        val who: String = "" //代码家
//)
//
//data class Resource(
//        val _id: String = "", //5a274946421aa90fe2f02cb8
//        val createdAt: String = "", //2017-12-06T09:35:02.292Z
//        val desc: String = "", //九张图让你了解最神秘的团体——程序员
//        val publishedAt: String = "", //2017-12-11T08:43:39.682Z
//        val source: String = "", //web
//        val type: String = "", //Resource
//        val url: String = "", //https://mp.weixin.qq.com/s?__biz=MzU3NzA0ODQzMw==&mid=2247483743&idx=1&sn=b9484e36e8712195cf35f152e0c575af
//        val used: Boolean = false, //true
//        val who: String = "" //陈宇明
//)
//
//data class Android(
//        val _id: String = "", //5a289393421aa90fe2f02cbb
//        val createdAt: String = "", //2017-12-07T09:04:19.514Z
//        val desc: String = "", //一篇文章告诉你FFmpeg环境的搭建和编译
//        val publishedAt: String = "", //2017-12-11T08:43:39.682Z
//        val source: String = "", //web
//        val type: String = "", //Android
//        val url: String = "", //http://mp.weixin.qq.com/s?__biz=MzI3OTU0MzI4MQ==&mid=100001398&idx=1&sn=e2f10368a6146669b483c249e13b3fee&chksm=6b476ae85c30e3fe509ec489028d071c199de24a0660e047b87980ef5ba0f216e7d69893f8a8#rd
//        val used: Boolean = false, //true
//        val who: String = "" //codeGoogler
//)
//
//data class Benefit(
//        val _id: String = "", //5a2dd04e421aa90fe2f02ccc
//        val createdAt: String = "", //2017-12-11T08:24:46.981Z
//        val desc: String = "", //12-11
//        val publishedAt: String = "", //2017-12-11T08:43:39.682Z
//        val source: String = "", //chrome
//        val type: String = "", //福利
//        val url: String = "", //http://7xi8d6.com1.z0.glb.clouddn.com/20171211082435_CCblJd_Screenshot.jpeg
//        val used: Boolean = false, //true
//        val who: String = "" //daimajia
//)