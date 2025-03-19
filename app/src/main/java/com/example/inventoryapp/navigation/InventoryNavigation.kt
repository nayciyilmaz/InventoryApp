package com.example.inventoryapp.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

@Composable
fun InventoryNavigation() {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = InventoryAppScreens.HomeScreen.route) {
        composable(route = InventoryAppScreens.HomeScreen.route){

        }
        composable(route = InventoryAppScreens.AddItemScreen.route){

        }
        composable(route = InventoryAppScreens.EditItemScreen.route){

        }
        composable(route = InventoryAppScreens.DetailsScreen.route){

        }
    }
}