package com.atplatform.test.bean

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "tb_transaction")
data class Transaction(
    @PrimaryKey(autoGenerate = true)
    var id: Int=0,
    val name: String?,
    val amount: Double,
    val quantity: Int,
    val time: Long
)