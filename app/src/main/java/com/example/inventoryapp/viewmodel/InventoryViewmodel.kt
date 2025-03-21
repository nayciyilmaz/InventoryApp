package com.example.inventoryapp.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.inventoryapp.repository.InventoryRepo
import com.example.inventoryapp.roomdb.Item
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class InventoryViewmodel @Inject constructor(private val repository: InventoryRepo) : ViewModel() {
    private val _uiState = MutableStateFlow<List<Item>>(emptyList())
    val uiState: StateFlow<List<Item>> = _uiState.asStateFlow()

    init {
        viewModelScope.launch {
            repository.allItems().collect { itemList ->
                _uiState.value = itemList
            }
        }
    }

    var inputUsername by mutableStateOf("")
        private set

    var inputPrice by mutableStateOf("")
        private set

    var inputQuantity by mutableStateOf("")
        private set

    fun updateName(newName: String) {
        inputUsername = newName
    }

    fun updatePrice(newPrice: String) {
        inputPrice = newPrice
    }

    fun updateQuantity(newQuantity: String) {
        inputQuantity = newQuantity
    }

    fun saveItem(name: String, quantity: String, price: String) {
        val newItem = Item(
            name = name,
            quantity = quantity.toIntOrNull() ?: 0,
            price = price.toDoubleOrNull() ?: 0.0
        )
        add(newItem)
    }

    fun clearItem() {
        inputUsername = ""
        inputPrice = ""
        inputQuantity = ""
    }

    fun add(item: Item) = viewModelScope.launch { repository.add(item) }
    fun delete(item: Item) = viewModelScope.launch { repository.delete(item) }
    fun update(item: Item) = viewModelScope.launch { repository.update(item) }
}