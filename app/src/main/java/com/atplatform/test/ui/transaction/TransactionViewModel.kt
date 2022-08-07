package com.atplatform.test.ui.transaction

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.atplatform.test.repository.FutureRepository
import com.atplatform.test.repository.TransactionRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class TransactionViewModel : ViewModel() {

    val transactions by lazy(LazyThreadSafetyMode.NONE) { TransactionRepository.queryAllTransactionRecord() }

    private val _rate = MutableLiveData<Double>()
    val rate: LiveData<Double> = _rate
    fun queryExchangeRate() {
        viewModelScope.launch {
            val exchangeRate = withContext(Dispatchers.IO) {
                FutureRepository.queryExchangeRate("CNY", "USD")
            }
            if (exchangeRate.code == 0) {
                _rate.value = exchangeRate.data?.rate
            }
        }
    }
}