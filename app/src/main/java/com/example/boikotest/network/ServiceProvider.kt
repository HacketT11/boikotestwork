package com.example.boikotest.network

import com.example.boikotest.BuildConfig
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

interface ServiceProvider {

    fun <T> provideService(klass: Class<T>): T
}

class RetrofitServiceProvider : ServiceProvider {

    private val retrofit: Retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(BuildConfig.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(ResultCallAdapterFactory())
            .build()
    }

    override fun <T> provideService(klass: Class<T>): T = retrofit.create(klass)
}