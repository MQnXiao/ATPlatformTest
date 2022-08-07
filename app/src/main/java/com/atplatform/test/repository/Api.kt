package com.atplatform.test.repository

import com.atplatform.test.bean.ExchangeRate
import retrofit2.http.GET
import retrofit2.http.Query

interface Api {

    @GET("gooking/forex/rate")
    suspend fun queryExchangeRate(
        @Query("fromCode") fromCode: String,
        @Query("toCode") toCode: String
    ): ExchangeRate
}