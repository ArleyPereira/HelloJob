package br.com.hellodev.hellojob.search.presenter.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.hellodev.common.domain.model.category.CategoryDomain
import br.com.hellodev.common.domain.model.home.HomeDomain
import br.com.hellodev.hellojob.search.presenter.action.SearchAction
import br.com.hellodev.hellojob.search.presenter.state.SearchState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class SearchViewModel : ViewModel() {

    private var _state = MutableStateFlow(SearchState())
    var state: StateFlow<SearchState> = _state

    init {
        initData()
    }

    fun dispatchAction(action: SearchAction) {
        when (action) {
            is SearchAction.OnSearchChange -> {
                onSearchChange(query = action.query)
            }

            is SearchAction.OnSearch -> {
                onSearch()
            }
        }
    }

    private fun initData() {
        viewModelScope.launch {
            val homeData = HomeDomain.homeDomainDefault

            _state.update { currentState ->
                currentState.copy(
                    isLoading = false
                )
            }
        }
    }

    private fun onSearchChange(query: String) {
        _state.update { currentState ->
            currentState.copy(query = query)
        }
    }

    private fun onSearch() {
//        val recentFake = if (_state.value.recent?.items?.isEmpty() == true) {
//            when (_state.value.categorySelected) {
//                CategoryDomain.items[1] -> JobItemDomain.designJobs
//                CategoryDomain.items[2] -> JobItemDomain.technologyJobs
//                CategoryDomain.items[3] -> JobItemDomain.financeJobs
//                CategoryDomain.items[4] -> JobItemDomain.educationJobs
//                CategoryDomain.items[5] -> JobItemDomain.healthJobs
//                CategoryDomain.items[6] -> JobItemDomain.marketingJobs
//                CategoryDomain.items[7] -> JobItemDomain.salesJobs
//                CategoryDomain.items[8] -> JobItemDomain.engineeringJobs
//                CategoryDomain.items[9] -> JobItemDomain.logisticsJobs
//                else -> JobItemDomain.items
//            }
//        } else {
//            _state.value.recent?.items
//        }
//
//        val query = _state.value.query
//        val filteredJobs = if (query.isNotEmpty()) {
//            recentFake?.filter { it.title?.contains(query, true) == true }
//        } else JobSectionDomain.items[1].items
//
//        val recent = JobSectionDomain.items[1].copy(items = filteredJobs)
//
//        _state.update { currentState ->
//            currentState.copy(
//                query = query,
//                recent = recent
//            )
//        }
    }

    private fun onCategorySelected(category: CategoryDomain?) {
//        val jobs = when (category) {
//            CategoryDomain.items[1] -> JobItemDomain.designJobs
//            CategoryDomain.items[2] -> JobItemDomain.technologyJobs
//            CategoryDomain.items[3] -> JobItemDomain.financeJobs
//            CategoryDomain.items[4] -> JobItemDomain.educationJobs
//            CategoryDomain.items[5] -> JobItemDomain.healthJobs
//            CategoryDomain.items[6] -> JobItemDomain.marketingJobs
//            CategoryDomain.items[7] -> JobItemDomain.salesJobs
//            CategoryDomain.items[8] -> JobItemDomain.engineeringJobs
//            CategoryDomain.items[9] -> JobItemDomain.logisticsJobs
//            else -> JobItemDomain.items
//        }
//
//        val query = _state.value.query
//        val jobsFiltered = jobs?.filter { it.title?.contains(query, true) == true }
//
//        val recent = JobSectionDomain.items[1].copy(items = jobsFiltered)
//
//        _state.update { currentState ->
//            currentState.copy(
//                categorySelected = category,
//                recent = recent
//            )
//        }
    }

}