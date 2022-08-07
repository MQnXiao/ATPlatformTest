package com.atplatform.test.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.atplatform.test.bean.Transaction

@Dao
interface TransactionDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertTransactionRecord(transaction: Transaction)

    @Query("SELECT * FROM tb_transaction ORDER BY time DESC")
    fun queryAllTransactionRecord(): LiveData<MutableList<Transaction>>
}