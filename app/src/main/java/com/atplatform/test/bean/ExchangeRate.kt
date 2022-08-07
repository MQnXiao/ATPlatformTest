package com.atplatform.test.bean

data class ExchangeRate(
    val code: Int ,
    val data: Data? = null,
    val msg: String = "",
)

data class Data(val rate: Double)
