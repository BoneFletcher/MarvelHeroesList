package com.sdv.attractgrouptesttask

import android.app.Application
import com.sdv.attractgrouptesttask.di.apiModule
import com.sdv.attractgrouptesttask.di.netModule
import com.sdv.attractgrouptesttask.di.repositoryModule
import com.sdv.attractgrouptesttask.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

/**
 * Created by FrostEagle on 04.05.2020.
 * My Email: denisshakhov21@gmail.com
 * Skype: lifeforlight
 */
class ProfileApp : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger()
            androidContext(this@ProfileApp)
            modules(listOf(netModule, apiModule, repositoryModule, viewModelModule))
        }
    }
}