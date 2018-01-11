package me.feixie.gank_kotlin.api

/**
 * Created by fei on 11/01/2018.
 */

data class ContentApiModel(
		val error: Boolean, //false
		val results: List<Result>
)

data class Result(
		val _id: String, //5a4af266421aa90fe50c02b6
		val createdAt: String, //2018-01-02T10:45:58.490Z
		val desc: String, //一个简洁、实用、方便的Android异步处理库，已应用到百万日活的线上项目中
		val publishedAt: String, //2018-01-10T07:57:19.486Z
		val source: String, //web
		val type: String, //Android
		val url: String, //https://github.com/SilenceDut/TaskScheduler
		val used: Boolean, //true
		val who: String? //null
)