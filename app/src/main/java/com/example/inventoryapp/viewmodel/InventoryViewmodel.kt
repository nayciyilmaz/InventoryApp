package com.example.inventoryapp.viewmodel

import com.example.inventoryapp.repository.InventoryRepo
import com.example.inventoryapp.roomdb.Item
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class InventoryViewmodel @Inject constructor(private val repository: InventoryRepo)  {
    private val _uiState = MutableStateFlow<List<Item>>(emptyList())
    val uiState : StateFlow<List<Item>> = _uiState.asStateFlow()
}