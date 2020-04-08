package com.github.boahandroid.di

import android.content.Context
import com.github.boahandroid.BaseApplication
import com.github.boahandroid.BuildConfig.BASE_URL
import com.github.boahandroid.data.remote.ApiServices
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
class AppModule {
    private val connectTimeout: Long = 30
    private val readTimeout: Long = 30
    private val writeTimeout: Long = 30

    @Provides
    fun providesContext(application: BaseApplication): Context{
        return application.applicationContext
    }

    @Provides
    @Singleton
    fun provideRequestService(): ApiServices{
        val interceptor = HttpLoggingInterceptor()
        interceptor.level = HttpLoggingInterceptor.Level.BODY
        val client = OkHttpClient.Builder()
            .connectTimeout(connectTimeout, TimeUnit.SECONDS)
            .readTimeout(readTimeout, TimeUnit.SECONDS)
            .writeTimeout(writeTimeout, TimeUnit.SECONDS)
            .addInterceptor(interceptor)
            .build()
        val gson = GsonBuilder().create()
        return Retrofit.Builder()
            .client(client)
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build().create(ApiServices::class.java)
    }
}