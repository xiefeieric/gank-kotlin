package me.feixie.gank_kotlin

import android.app.Application
import com.squareup.leakcanary.LeakCanary
import net.danlew.android.joda.JodaTimeAndroid
import timber.log.Timber

/**
 * Created by fei on 11/12/2017.
 */
class GankApplication: Application() {

    companion object {
        lateinit var instance:GankApplication
    }

    override fun onCreate() {
        super.onCreate()
        instance = this
        JodaTimeAndroid.init(this);

        //development only features
        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())

            if (LeakCanary.isInAnalyzerProcess(this)) {
                // This process is dedicated to LeakCanary for heap analysis.
                // You should not init your app in this process.
                return;
            }
            LeakCanary.install(this);
        }
    }

}