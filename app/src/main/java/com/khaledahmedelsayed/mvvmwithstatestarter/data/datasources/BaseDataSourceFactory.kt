package com.khaledahmedelsayed.mvvmwithstatestarter.data.datasources

import androidx.lifecycle.MutableLiveData
import androidx.paging.DataSource
import androidx.paging.PageKeyedDataSource
import com.khaledahmedelsayed.mvvmwithstatestarter.common.helpers.ContextProviders
import com.khaledahmedelsayed.mvvmwithstatestarter.common.Utils.getCoroutineExceptionHandler
import com.khaledahmedelsayed.mvvmwithstatestarter.data.remote.responses.base.ApiBaseResponseArrayOfData
import com.khaledahmedelsayed.mvvmwithstatestarter.ui.platform.base.BaseViewState
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

abstract class BaseDataSourceFactory<PageListObject>(
    private val contextProviders: ContextProviders,
    private val internalState: MutableLiveData<BaseViewState>
) : DataSource.Factory<Int, PageListObject>() {

    override fun create(): DataSource<Int, PageListObject>? =
        BaseDataSource(contextProviders = contextProviders)

    protected open var initialPage = 1

    abstract suspend fun getInitialList(): ApiBaseResponseArrayOfData<PageListObject>

    abstract suspend fun getNextList(nextPage: Int): ApiBaseResponseArrayOfData<PageListObject>

    inner class BaseDataSource constructor(val contextProviders: ContextProviders) :
        PageKeyedDataSource<Int, PageListObject>() {

        private fun launchSuspendingBlock(
            displayLoader: Boolean = true,
            block: suspend CoroutineScope.() -> Unit
        ) {
            internalState.postValue(BaseViewState.Loading(displayLoader))
            CoroutineScope(contextProviders.io + getCoroutineExceptionHandler(internalState)).launch {
                block.invoke(this)
                internalState.postValue(BaseViewState.Loaded.instance)
            }
        }

        override fun loadInitial(
            params: LoadInitialParams<Int>,
            callback: LoadInitialCallback<Int, PageListObject>

        ) {
            launchSuspendingBlock {
                val response = getInitialList()
                val nextPage = if (response.meta?.lastPage == initialPage) null else initialPage + 1
                callback.onResult(response.data ?: arrayListOf(), null, nextPage)
            }
        }

        override fun loadAfter(
            params: LoadParams<Int>,
            callback: LoadCallback<Int, PageListObject>
        ) {

            launchSuspendingBlock(false) {
                val response = getNextList(params.key)
                val nextPage = if (response.meta?.lastPage == params.key) null else params.key + 1
                callback.onResult(response.data ?: arrayListOf(), nextPage)
            }
        }

        override fun loadBefore(
            params: LoadParams<Int>,
            callback: LoadCallback<Int, PageListObject>
        ) {
            // Ignore
        }
    }
}
