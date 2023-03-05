package com.example.boikotest

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.boikotest.MainNavigation.LIST_ROUTE
import com.example.boikotest.MainNavigation.USER_ROUTE
import com.example.boikotest.MainNavigation.USER_ROUTE_ARG
import com.example.boikotest.screens.list.ListScreen
import com.example.boikotest.screens.user.UserScreen
import org.koin.androidx.compose.koinViewModel
import org.koin.core.parameter.parametersOf

object MainNavigation {
    const val LIST_ROUTE = "list"

    const val USER_ROUTE_ARG = "id"
    const val USER_ROUTE = "user/"
}

@Composable
fun MainNavigationHost(navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = LIST_ROUTE,
        builder = {
            composable(route = LIST_ROUTE) {
                ListScreen(
                    listViewModel = koinViewModel(),
                    navController = navController
                )
            }
            composable(
                route = "$USER_ROUTE{$USER_ROUTE_ARG}",
                arguments = listOf(navArgument(USER_ROUTE_ARG) { type = NavType.IntType })
            ) {
                val userId = requireNotNull(it.arguments?.getInt(USER_ROUTE_ARG)) {
                    "userId is required for UserScreen"
                }
                UserScreen(
                    userViewModel = koinViewModel { parametersOf(userId) },
                    navController = navController
                )
            }
        }
    )
}