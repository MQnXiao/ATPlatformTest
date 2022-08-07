package com.atplatform.test.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.atplatform.test.bean.Transaction

@Database(entities = [Transaction::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
    abstract fun transactionDao(): TransactionDao
}