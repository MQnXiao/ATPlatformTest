package com.atplatform.test.repository

import com.atplatform.test.bean.ExchangeRate
import com.atplatform.test.bean.FuturesResp
import com.atplatform.test.net.RetrofitManager

object FutureRepository {


    suspend fun queryExchangeRate(fromCode: String, toCode: String): ExchangeRate {

        return RetrofitManager.getApi(Api::class.java).queryExchangeRate(fromCode, toCode)
    }

    suspend fun getFutureInfo(ftsId: String): FuturesResp {
        return RetrofitManager.getFutureApi(FutureApi::class.java).queryFutureInfo(
            "quote.futures",
            ftsId,
            "66852",
            "0d6d6e5ac63ea44c6349c4200bc64598",
            "json"
        )
    }
}