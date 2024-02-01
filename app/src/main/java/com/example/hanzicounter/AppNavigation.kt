package com.example.hanzicounter

import androidx.navigation.NavController
import com.example.hanzicounter.AppDestinations.READ_MODE_ROUTE
import com.example.hanzicounter.AppDestinations.WRITE_MODE_ROUTE

object AppDestinations{
    const val WRITE_MODE_ROUTE = "writeMode"
    const val READ_MODE_ROUTE = "readMode"
}

class NavigationActions(private val navController: NavController){

    fun navigateToWriteModeScreen(){
        navController.navigate(WRITE_MODE_ROUTE)
    }
    fun navigateToReadModeScreen() {
        navController.navigate(READ_MODE_ROUTE){
            launchSingleTop = true
        }
    }

    fun navigateBack() {
        navController.popBackStack()
    }
}