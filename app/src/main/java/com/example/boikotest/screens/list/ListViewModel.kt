package com.example.boikotest.screens.list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.example.boikotest.domain.GetPagedUsersUseCase
import com.example.boikotest.domain.SetFavoriteUseCase
import com.example.boikotest.domain.SetFilterForPagedUsersUseCase
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class ListViewModel(
    getPagedUsersUseCase: GetPagedUsersUseCase,
    private val setFilterForPagedUsersUseCase: SetFilterForPagedUsersUseCase,
    private val setFavoriteUseCase: SetFavoriteUseCase
) : ViewModel() {

    private val _state = MutableStateFlow(
        ListScreenState(
            // just a fast workaround for single state approach
            usersPagingData = getPagedUsersUseCase.invoke().cachedIn(viewModelScope)
        )
    )
    val state = _state.asStateFlow()

    private val _events = MutableSharedFlow<ListScreenEvent>()
    val events = _events.asSharedFlow()


    fun onSelectAsFavorite(userId: Int, isFavorite: Boolean) = viewModelScope.launch {
        setFavoriteUseCase.invoke(userId, !isFavorite)
    }

    fun showOnlyFavorites() {
        val currentFilter = _state.value.isOnlyFavorites
        setFilterForPagedUsersUseCase.invoke(!currentFilter)
        _state.value = _state.value.copy(isOnlyFavorites = !currentFilter)
    }
}