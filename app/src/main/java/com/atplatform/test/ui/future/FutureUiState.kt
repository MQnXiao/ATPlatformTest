package com.atplatform.test.ui.future

import com.atplatform.test.bean.Future

sealed class FutureUiState {

    object Idle : FutureUiState()

    object IsLoading : FutureUiState()

    data class LoadError(val error: Exception) : FutureUiState()

    data class LoadSuccess(val reqData: MutableList<Future>) : FutureUiState()

}
