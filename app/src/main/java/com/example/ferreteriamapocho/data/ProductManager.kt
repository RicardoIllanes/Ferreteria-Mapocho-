package com.example.ferreteriamapocho.data

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow


object ProductManager {

    private val _products = MutableStateFlow(Repo.products.toMutableList())
    val products: StateFlow<List<Product>> = _products

    private val _selectedCategory = MutableStateFlow("Todo")
    val selectedCategory: StateFlow<String> = _selectedCategory

    fun filterBy(category: String) {
        _selectedCategory.value = category
    }

    fun getFiltered(): List<Product> {
        return if (_selectedCategory.value == "Todo") {
            _products.value
        } else {
            _products.value.filter { it.category == _selectedCategory.value }
        }
    }

    fun decreaseStock(productId: Int): Boolean {
        val updated = _products.value.toMutableList()
        val index = updated.indexOfFirst { it.id == productId }

        if (index != -1 && updated[index].stock > 0) {
            updated[index].stock -= 1
            _products.value = updated
            return true
        }
        return false
    }
}