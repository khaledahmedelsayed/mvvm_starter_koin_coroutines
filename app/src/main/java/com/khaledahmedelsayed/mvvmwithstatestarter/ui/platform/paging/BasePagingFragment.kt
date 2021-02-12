package com.khaledahmedelsayed.mvvmwithstatestarter.ui.platform.paging

import androidx.databinding.ViewDataBinding
import androidx.lifecycle.Observer
import androidx.paging.PagedList
import com.khaledahmedelsayed.mvvmwithstatestarter.ui.platform.base.BaseFragment
import com.khaledahmedelsayed.mvvmwithstatestarter.ui.platform.base.BaseViewState

abstract class BasePagingFragment<
    VDB : ViewDataBinding,
    VM : BasePagingViewModel<PageListObject>,
    VS : BaseViewState,
    PageListObject : Any> :
    BaseFragment<VDB, VM, VS>() {

    abstract fun onPageLoaded(pagedListKey: Int, pagedList: PagedList<PageListObject>)

    private fun clearPagingLiveDataObservers() {
        for (listLiveDataKey in viewModel.pagedListsLiveDataMap.keys)
            if (viewModel.isPagingLiveDataInitialized(listLiveDataKey))
                viewModel.pagedListsLiveDataMap[listLiveDataKey]?.removeObservers(
                    viewLifecycleOwner
                )
    }

    private fun setupPagingLiveDataObservers() {
        for (key in viewModel.pagedListsLiveDataMap.keys)
            viewModel.pagedListsLiveDataMap[key]?.observe(
                this,
                Observer { pagedList ->
                    onPageLoaded(key, pagedList)
                }
            )
    }

    protected fun fetchPagingData() { // Call this function to load/reload List
        clearPagingLiveDataObservers()
        viewModel.setupPagingLiveData()
        setupPagingLiveDataObservers()
    }
}
