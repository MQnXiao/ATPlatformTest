package com.atplatform.test.db

import androidx.room.Room
import com.atplatform.test.App

class DatabaseHelper private constructor() {

    private val mAppDatabase: AppDatabase = Room.databaseBuilder(
        App.instance.applicationContext,
        AppDatabase::class.java,
        "transaction.db"
    ) //.addMigrations()
        .build()

    fun getAppDatabase(): AppDatabase {
        return mAppDatabase
    }

    private object SingleHelper {
        val database: DatabaseHelper = DatabaseHelper()
    }

    companion object {
        val instance = SingleHelper.database
    }
}
