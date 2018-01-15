package me.feixie.gank_kotlin.android

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import es.dmoral.toasty.Toasty
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import me.feixie.gank_kotlin.GankApplication
import me.feixie.gank_kotlin.api.ContentApiModel
import me.feixie.gank_kotlin.dagger.ApiServiceModule
import me.feixie.gank_kotlin.dagger.DaggerAppComponent
import me.feixie.gank_kotlin.dagger.DaggerServiceComponent
import timber.log.Timber

/**
 * Created by fei on 11/01/2018.
 */
class AndroidViewModel:ViewModel() {

    private val mDisposable = CompositeDisposable()
    private val mService = DaggerServiceComponent.builder().apiServiceModule(ApiServiceModule()).build().injectService()
    private val mLiveAndroidContent = MutableLiveData<ContentApiModel>()

    fun getLiveAndroidContent(type:String): MutableLiveData<ContentApiModel> {
        mService.getAndroidContent(type)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({androidContent ->
                    mLiveAndroidContent.value = androidContent
                }, { error ->
                    Timber.d("Android content load error")
                    Timber.d(error.toString())
                    error.message?.let {
                        Toasty.error(GankApplication.instance, it).show()
                    }
                })
        return mLiveAndroidContent
    }

    override fun onCleared() {
        super.onCleared()
        mDisposable.clear()

    }
}