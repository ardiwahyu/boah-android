package com.github.boahandroid.di

import com.github.boahandroid.BaseApplication
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        AndroidSupportInjectionModule::class,
        ActivityModule::class,
        AppModule::class,
        ViewModelModule::class,
        FragmentModule::class
    ]
)
interface AppComponent: AndroidInjector<BaseApplication> {
    override fun inject(instance: BaseApplication?)

    @Component.Builder
    interface Builder{
        @BindsInstance
        fun application(application: BaseApplication): Builder

        fun build(): AppComponent
    }
}