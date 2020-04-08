package com.github.boahandroid

import com.facebook.stetho.Stetho
import com.github.boahandroid.di.DaggerAppComponent
import dagger.android.AndroidInjector
import dagger.android.DaggerApplication
import timber.log.Timber
import java.util.*

class BaseApplication: DaggerApplication() {
    private val appComponent = DaggerAppComponent.builder()
        .application(this)
        .build()

    override fun applicationInjector(): AndroidInjector<out DaggerApplication> {
        return appComponent
    }

    override fun onCreate() {
        super.onCreate()
        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }
        Stetho.initializeWithDefaults(this)
        TimeZone.setDefault(TimeZone.getTimeZone("GMT+7"))
    }

}