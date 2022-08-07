package com.atplatform.test.ui.future

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.atplatform.test.bean.Future
import com.atplatform.test.bean.Transaction
import com.atplatform.test.repository.FutureRepository
import com.atplatform.test.repository.TransactionRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class FutureViewModel : ViewModel() {

    private val _data = MutableStateFlow<FutureUiState>(FutureUiState.Idle)
    val futureState: StateFlow<FutureUiState> = _data

    private val futureIds = mutableListOf(
        "31001",
        "31003",
        "31004",
        "31006",
        "31007",
        "31008",
        "31036",
        "31037"
    )

    fun saveTransaction(transaction: Transaction) {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                TransactionRepository.insertTransactionRecord(transaction)
            }
        }
    }

    fun getFutures() {
        viewModelScope.launch {
            val result = mutableListOf<Future>()
            _data.value = FutureUiState.IsLoading
            try {
                futureIds.asFlow()
                    .flowOn(Dispatchers.IO)
                    .map { id -> FutureRepository.getFutureInfo(id) }
                    .filter { it.result?.dtList?.isNotEmpty() == true }
                    .map { it.result!!.dtList[it.result.dtQuery] }
                    .collect { value ->
                        value?.let { result.add(it) }
                    }
                _data.value = FutureUiState.LoadSuccess(result)
            } catch (e: Exception) {
                _data.value = FutureUiState.LoadError(e)
            }

        }
    }
}