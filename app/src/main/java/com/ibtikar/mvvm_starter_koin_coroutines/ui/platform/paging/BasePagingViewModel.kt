package com.ibtikar.mvvm_starter_koin_coroutines.ui.platform.paging

import androidx.lifecycle.LiveData
import androidx.paging.DataSource
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.ibtikar.mvvm_starter_koin_coroutines.common.helpers.ContextProviders
import com.ibtikar.mvvm_starter_koin_coroutines.ui.platform.base.BaseViewModel

abstract class BasePagingViewModel<PageListObject>(
    contextProvider: ContextProviders
) : BaseViewModel(contextProvider) {

    internal var pagedListsLiveDataMap = mutableMapOf<Int, LiveData<PagedList<PageListObject>>>()

    abstract fun setupPagingLiveData() // use buildPagingLiveData() in its implementation

    fun buildPagingLiveData(
        listKey: Int,
        dataSourceFactory: DataSource.Factory<Int, PageListObject>,
        itemsPerPage: Int
    ) {
        val pagedListConfig = PagedList.Config.Builder().setPageSize(itemsPerPage).build()
        pagedListsLiveDataMap[listKey] =
            LivePagedListBuilder(dataSourceFactory, pagedListConfig).build()
    }

    fun isPagingLiveDataInitialized(listKey: Int) = pagedListsLiveDataMap[listKey] != null
}
