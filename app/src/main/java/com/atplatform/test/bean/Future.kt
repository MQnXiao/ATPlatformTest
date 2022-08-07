package com.atplatform.test.bean

data class FuturesResp(
    val success: String = "",
    val result: Result? = null
)

data class Result(
    val dtQuery: String = "",
    val dtCount: String = "",
    val dtList: Map<String, Future> = emptyMap()
)

data class Future(
    val ftsId: String = "",
    val ftsType: String = "",
    val ftsNm: String = "",
    val lastPrice: String = "",
    val highPrice: String = "",
    val lowPrice: String = "",
    val changeMargin: String = "",
    val upTime: String = ""
)