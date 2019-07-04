package com.perry.wanandroid.kt.base

import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.perry.wanandroid.kt.model.bean.RequestStatus
import com.perry.wanandroid.kt.model.bean.Response
import kotlinx.coroutines.*

open class BaseViewModel : ViewModel(), LifecycleObserver {

    var requestStatus: MutableLiveData<RequestStatus> = MutableLiveData()
    var errorMsg: MutableLiveData<String> = MutableLiveData()
    var srlStatus: MutableLiveData<RequestStatus> = MutableLiveData()
    var loadMoreStatus: MutableLiveData<RequestStatus> = MutableLiveData()

    private fun launchOnUI(block: suspend CoroutineScope.() -> Unit) = viewModelScope.launch { block() }

    suspend fun <T> launchIO(block: suspend CoroutineScope.() -> T) {
        withContext(Dispatchers.IO) {
            block
        }
    }

    fun launch(tryBlock: suspend CoroutineScope.() -> Unit) {
        launchOnUI {
            tryCatch(tryBlock, {}, {}, true)
        }
    }

    private suspend fun tryCatch(
            tryBlock: suspend CoroutineScope.() -> Unit,
            catchBlock: suspend CoroutineScope.(Throwable) -> Unit,
            finallyBlock: suspend CoroutineScope.() -> Unit,
            handleCancellationExceptionManually: Boolean = false
    ) {
        coroutineScope {
            try {
                tryBlock()
            } catch (e: Exception) {
                if (e !is CancellationException || handleCancellationExceptionManually) {
                    catchBlock(e)
                } else {
                    throw e
                }
            } finally {
                finallyBlock()
            }
        }
    }

    suspend fun <T> resolveResponse(
            result: Response<T>,
            successBlock: suspend CoroutineScope.() -> Unit,
            errorBlock: suspend CoroutineScope.() -> Unit,
            isLoadMore: Boolean = false
    ) {
        coroutineScope {
            if (result.errorCode == 0) {
                requestStatus.value = RequestStatus.SUCCESS
                srlStatus.value = RequestStatus.SUCCESS
                if (isLoadMore) loadMoreStatus.value = RequestStatus.SUCCESS
                successBlock()
            } else {
                //TODO: add error handle
                errorMsg.value = result.errorMsg
                requestStatus.value = RequestStatus.ERROR
                srlStatus.value = RequestStatus.ERROR
                if (isLoadMore) loadMoreStatus.value = RequestStatus.ERROR
                errorBlock()
            }
        }
    }
}