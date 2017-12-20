package me.feixie.gank_kotlin

import android.app.Activity
import android.support.v4.app.FragmentManager
import me.feixie.gank_kotlin.android.AndroidFragment
import me.feixie.gank_kotlin.today.TodayFragment

/**
 * Created by fei on 11/12/2017.
 */

fun Activity.showFragment(fm:FragmentManager, tag:String) {
    var fragment = fm.findFragmentByTag(tag)
    if (fragment == null) {
        when (tag) {
            MainActivity.TODAY_FRAGMENT -> {
                fragment = TodayFragment.newInstance()
            }
            MainActivity.ANDROID_FRAGMENT -> {
                fragment = AndroidFragment.newInstance()
            }
            else -> {
                throw IllegalArgumentException("tag cannot find")
            }
        }
    }
    fm.beginTransaction().replace(R.id.flContainer, fragment).commit()
}