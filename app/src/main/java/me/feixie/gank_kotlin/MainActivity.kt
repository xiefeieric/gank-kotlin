package me.feixie.gank_kotlin

import android.os.Bundle
import android.support.design.widget.NavigationView
import android.support.v4.view.GravityCompat
import android.support.v7.app.ActionBarDrawerToggle
import android.support.v7.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.app_bar_main.*
import me.feixie.gank_kotlin.today.TodayFragment

class MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {

    private lateinit var mTodayFragment:TodayFragment

    companion object {
        val TODAY_FRAGMENT = "today_fragment"
        val ANDROID_FRAGMENT = "android_fragment"
        val BENEFIT_FRAGMENT = "benefit_fragment"
        val ABOUT_FRAGMENT = "about_fragment"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initViews()
    }

    private fun initViews() {
        setSupportActionBar(toolbar)

        val toggle = ActionBarDrawerToggle(
                this, drawer_layout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close)
        drawer_layout.addDrawerListener(toggle)
        toggle.syncState()

        nav_view.setNavigationItemSelectedListener(this)
        nav_view.setCheckedItem(R.id.nav_today)

        mTodayFragment = if (supportFragmentManager.findFragmentByTag(TODAY_FRAGMENT) == null) {
            TodayFragment()
        } else {
            supportFragmentManager.findFragmentByTag(TODAY_FRAGMENT) as TodayFragment
        }
        supportFragmentManager.beginTransaction().replace(R.id.flContainer, mTodayFragment, TODAY_FRAGMENT).commit()
    }

    override fun onBackPressed() {
        if (drawer_layout.isDrawerOpen(GravityCompat.START)) {
            drawer_layout.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        when (item.itemId) {
            R.id.action_settings -> return true
            else -> return super.onOptionsItemSelected(item)
        }
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        // Handle navigation view item clicks here.
        when (item.itemId) {
            R.id.nav_today -> {
                // Handle the camera action
                showFragment(supportFragmentManager, TODAY_FRAGMENT, null)
                supportActionBar?.title = "Gank"
            }
            R.id.nav_android -> {
                showFragment(supportFragmentManager, ANDROID_FRAGMENT, "Android")
                supportActionBar?.title = "Android"
            }
            R.id.nav_ios -> {
                showFragment(supportFragmentManager, ANDROID_FRAGMENT, "iOS")
                supportActionBar?.title = "iOS"
            }
            R.id.nav_front_end -> {
                showFragment(supportFragmentManager, ANDROID_FRAGMENT, "前端")
                supportActionBar?.title = "前端"
            }
            R.id.nav_benefit -> {
                showFragment(supportFragmentManager, BENEFIT_FRAGMENT, null)
                supportActionBar?.title = "福利"
            }
            R.id.nav_videos -> {
                showFragment(supportFragmentManager, ANDROID_FRAGMENT, "休息视频")
                supportActionBar?.title = "休息视频"
            }
            R.id.nav_about -> {
                showFragment(supportFragmentManager, ABOUT_FRAGMENT, null)
                supportActionBar?.title = "关于"
            }
//            R.id.nav_feedback -> {
//
//            }
        }

        drawer_layout.closeDrawer(GravityCompat.START)
        return true
    }
}
