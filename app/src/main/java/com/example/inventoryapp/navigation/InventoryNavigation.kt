package com.example.inventoryapp.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.inventoryapp.screens.AddItemScreen
import com.example.inventoryapp.screens.HomeScreen
import com.example.inventoryapp.viewmodel.InventoryViewmodel

@Composable
fun InventoryNavigation(viewModel: InventoryViewmodel = hiltViewModel()) {
    val navController = rememberNavController()
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    NavHost(
        navController = navController,
        startDestination = InventoryAppScreens.HomeScreen.route
    ) {
        composable(route = InventoryAppScreens.HomeScreen.route) {
            HomeScreen(
                navController = navController,
                itemList = uiState
            )
        }
        composable(route = InventoryAppScreens.AddItemScreen.route) {
            AddItemScreen(
                navController = navController,
                quantity = viewModel.inputQuantity,
                price = viewModel.inputPrice,
                userName = viewModel.inputUsername,
                setName = { viewModel.updateName(it) },
                setPrice = { viewModel.updatePrice(it) },
                setQuantity = { viewModel.updateQuantity(it) },
                saveItem = { name, quantity, price -> viewModel.saveItem(name, quantity, price) },
                clearItem = { viewModel.clearItem() }
            )
        }
        composable(route = "EditItemScreen/{itemId}") {

        }
        composable(route = "DetailsScreen/{itemId}") {

        }
    }
}