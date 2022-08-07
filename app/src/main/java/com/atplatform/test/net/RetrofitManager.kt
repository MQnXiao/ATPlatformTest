package com.atplatform.test.net

import com.atplatform.test.BuildConfig
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit


object RetrofitManager {
    private const val BASE_URL = "https://api.it120.cc/"
    private const val FUTURE_URL = "https://sapi.k780.com"

    private val mRetrofit by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
    private val futureRetrofit by lazy {
        Retrofit.Builder()
            .baseUrl(FUTURE_URL)
            .client(configClient())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    private fun configClient(): OkHttpClient {
        return OkHttpClient.Builder().apply {
            if (BuildConfig.DEBUG) {
                addNetworkInterceptor(HttpLoggingInterceptor().apply {
                    level = HttpLoggingInterceptor.Level.BODY
                })
            }
        }.build()
    }

    fun <T> getApi(apiClass: Class<T>): T {
        return mRetrofit.create(apiClass)
    }

    fun <T> getFutureApi(apiClass: Class<T>): T {
        return futureRetrofit.create(apiClass)
    }


}