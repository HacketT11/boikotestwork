package com.example.boikotest.screens.user

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.remember
import androidx.navigation.NavController

@Composable
fun UserScreen(userViewModel: UserViewModel, navController: NavController) {

    val state = userViewModel.state.collectAsState()

    val onBack: () -> Unit = remember {
        {
            navController.popBackStack()
        }
    }

    UserScreenComponent(state = state.value, onBack = onBack)
    UserScreenEventProcessor(events = userViewModel.events)
}