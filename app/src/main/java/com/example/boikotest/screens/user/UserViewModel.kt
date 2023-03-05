package com.example.boikotest.screens.user

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.boikotest.domain.GetUserUseCase
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class UserViewModel(
    private val userId: Int,
    private val getUserUseCase: GetUserUseCase
) : ViewModel() {

    private val _state = MutableStateFlow(UserScreenState())
    val state = _state.asStateFlow()

    private val _events = MutableSharedFlow<UserScreenEvent>()
    val events = _events.asSharedFlow()

    init {
        loadUser()
    }

    private fun loadUser() = viewModelScope.launch {
        getUserUseCase.invoke(userId)
            .onSuccess {
                _state.value = _state.value.copy(user = it, isLoading = false)
            }.onFailure {
                _state.value = _state.value.copy(isLoading = false)
                _events.emit(CallError)
            }
    }
}