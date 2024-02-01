package com.example.hanzicounter

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.hanzicounter.compose.textreadmode.TextReadModeScreen
import com.example.hanzicounter.compose.textwritemode.TextWriteModeScreen

@Composable
fun AppNavGraph(
    modifier: Modifier,
    navController: NavHostController,
    startDestination: String = AppDestinations.READ_MODE_ROUTE,
    navActions: NavigationActions = remember(navController) {
        NavigationActions(navController)
    }
) {
    NavHost(
        navController = navController,
        startDestination = startDestination,
        modifier = modifier
    ){
        composable(AppDestinations.WRITE_MODE_ROUTE){
            TextWriteModeScreen(
                modifier = modifier,
                onNavigateToTextReadScreen = { navActions.navigateToReadModeScreen() },
                onNavigateBack = { navActions.navigateBack() }
            )
        }

        composable(AppDestinations.READ_MODE_ROUTE){
            TextReadModeScreen(
                modifier = modifier,
                onNavigateToTextWriteScreen = { navActions.navigateToWriteModeScreen() }
            )
        }
    }
}