package com.example.boikotest.screens.list

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.remember
import androidx.navigation.NavController
import com.example.boikotest.MainNavigation.USER_ROUTE

@Composable
fun ListScreen(listViewModel: ListViewModel, navController: NavController) {

    val state = listViewModel.state.collectAsState()

    val onUserClicked: (Int) -> Unit = remember {
        {
            navController.navigate("$USER_ROUTE$it")
        }
    }

    ListScreenComponent(
        onSelectUserAsFavorite = listViewModel::onSelectAsFavorite,
        onUserClicked = onUserClicked,
        onOnlyFavorite = listViewModel::showOnlyFavorites,
        state = state.value
    )
}