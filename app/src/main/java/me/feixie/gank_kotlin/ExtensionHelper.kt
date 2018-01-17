package me.feixie.gank_kotlin

import android.app.Activity
import android.os.Bundle
import android.support.v4.app.FragmentManager
import me.feixie.gank_kotlin.about.AboutFragment
import me.feixie.gank_kotlin.android.AndroidFragment
import me.feixie.gank_kotlin.benefit.BenefitFragment
import me.feixie.gank_kotlin.today.TodayFragment

/**
 * Created by fei on 11/12/2017.
 */

val CONTENT_TYPE = "content_type"

fun Activity.showFragment(fm:FragmentManager, tag:String, type:String?) {
    var fragment = fm.findFragmentByTag(tag)
    if (fragment == null) {
        fragment = when (tag) {
            MainActivity.TODAY_FRAGMENT -> {
                TodayFragment.newInstance()
            }
            MainActivity.BENEFIT_FRAGMENT -> {
                BenefitFragment.newInstance()
            }
            MainActivity.ANDROID_FRAGMENT -> {
                AndroidFragment.newInstance()
            }
            MainActivity.ABOUT_FRAGMENT -> {
                AboutFragment.newInstance()
            }
            else -> {
                throw IllegalArgumentException("tag cannot find")
            }
        }
    }
    type?.apply {
        val bundle = Bundle()
        bundle.putString(CONTENT_TYPE, type)
        fragment.arguments = bundle
    }
    fm.beginTransaction().replace(R.id.flContainer, fragment).commit()
}