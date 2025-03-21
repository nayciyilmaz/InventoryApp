package com.example.inventoryapp.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.inventoryapp.R
import com.example.inventoryapp.components.EditTopAppBar
import com.example.inventoryapp.navigation.InventoryAppScreens
import com.example.inventoryapp.roomdb.Item
import java.text.NumberFormat

@Composable
fun HomeScreen(
    navController: NavController,
    itemList: List<Item>,
    modifier: Modifier = Modifier
) {
    Scaffold(
        topBar = {
            EditTopAppBar(
                title = R.string.inventory,
                canNavigateBack = false,
                navController = navController
            )
        }
    ) { innerPadding ->
        Box(
            modifier = modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {
            Column(
                modifier = modifier
                    .fillMaxWidth()
                    .padding(bottom = 64.dp)
            ) {
                if (itemList.isEmpty()) {
                    Text(
                        text = stringResource(R.string.description),
                        style = MaterialTheme.typography.titleLarge,
                        textAlign = TextAlign.Center,
                        modifier = Modifier.fillMaxWidth()
                    )
                } else {
                    LazyColumn {
                        items(itemList) { item ->
                            Card(
                                modifier = modifier
                                    .fillMaxWidth()
                                    .padding(16.dp),
                                shape = RoundedCornerShape(topEnd = 24.dp, bottomStart = 24.dp),
                                colors = CardDefaults.cardColors(containerColor = Color(0xFFE0DFDF)),
                                onClick = { navController.navigate("${InventoryAppScreens.DetailsScreen.route}/${item.id}") }
                            ) {
                                Column(
                                    modifier = modifier
                                        .fillMaxSize()
                                        .padding(16.dp),
                                    verticalArrangement = Arrangement.spacedBy(16.dp)
                                ) {
                                    Row(modifier = Modifier.fillMaxWidth()) {
                                        Text(
                                            text = item.name,
                                            style = MaterialTheme.typography.bodyLarge
                                        )
                                        Spacer(modifier = modifier.weight(1f))
                                        Text(
                                            text = NumberFormat.getCurrencyInstance()
                                                .format(item.price),
                                            style = MaterialTheme.typography.bodyMedium
                                        )
                                    }
                                    Text(
                                        text = stringResource(R.string.in_stock, item.quantity),
                                        style = MaterialTheme.typography.bodyMedium
                                    )
                                }
                            }
                        }
                    }
                }
            }
            Box(
                modifier = modifier
                    .padding(12.dp)
                    .background(
                        color = Color(0xFFE0DFDF),
                        shape = RoundedCornerShape(topEnd = 24.dp, bottomStart = 24.dp)
                    )
                    .align(Alignment.BottomEnd)
            ) {
                IconButton(
                    onClick = { navController.navigate(InventoryAppScreens.AddItemScreen.route) },
                ) {
                    Icon(
                        imageVector = Icons.Default.Add,
                        contentDescription = null,
                    )
                }
            }
        }
    }
}