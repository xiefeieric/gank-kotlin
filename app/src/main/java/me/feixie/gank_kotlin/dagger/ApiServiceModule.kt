package me.feixie.gank_kotlin.dagger

import dagger.Component
import dagger.Module
import dagger.Provides
import me.feixie.gank_kotlin.api.ApiService
import javax.inject.Singleton

/**
 * Created by fei on 18/12/2017.
 */
@Module
class ApiServiceModule {
    @Provides
    @Singleton
    fun provideService():ApiService = ApiService
}

@Component(modules = [ApiServiceModule::class])
@Singleton
interface ServiceComponent {
    fun injectService():ApiService
}