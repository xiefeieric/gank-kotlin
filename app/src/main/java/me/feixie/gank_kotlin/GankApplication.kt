package me.feixie.gank_kotlin

import android.app.Application
import android.app.DatePickerDialog
import com.squareup.leakcanary.LeakCanary
import dagger.internal.DaggerCollections
import me.feixie.gank_kotlin.dagger.AppComponent
import me.feixie.gank_kotlin.dagger.AppModule
import me.feixie.gank_kotlin.dagger.DaggerAppComponent
import net.danlew.android.joda.JodaTimeAndroid
import timber.log.Timber

/**
 * Created by fei on 11/12/2017.
 */
class GankApplication : Application() {

    lateinit var component: AppComponent

    companion object {
        lateinit var instance: GankApplication
    }

    override fun onCreate() {
        super.onCreate()
        instance = this
        JodaTimeAndroid.init(this);
        component = DaggerAppComponent.builder()
                .appModule(AppModule())
                .build()


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