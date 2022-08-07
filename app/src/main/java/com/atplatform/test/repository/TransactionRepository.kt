package com.atplatform.test.repository

import androidx.lifecycle.LiveData
import com.atplatform.test.db.DatabaseHelper
import com.atplatform.test.bean.Transaction
import com.atplatform.test.db.TransactionDao

object TransactionRepository : TransactionDao {
    override fun insertTransactionRecord(transaction: Transaction) {
        DatabaseHelper.instance.getAppDatabase().transactionDao().insertTransactionRecord(transaction)
    }

    override fun queryAllTransactionRecord(): LiveData<MutableList<Transaction>> {
        return DatabaseHelper.instance.getAppDatabase().transactionDao().queryAllTransactionRecord()
    }
}