package com.atplatform.test.repository

import com.atplatform.test.bean.FuturesResp
import retrofit2.http.GET
import retrofit2.http.Query

interface FutureApi {

    @GET("/")
    suspend fun queryFutureInfo(
        @Query("app") app: String,
        @Query("ftsIdS") ftsIdS: String,
        @Query("appkey") appkey: String,
        @Query("sign") sign: String,
        @Query("format") format: String
    ): FuturesResp
}