package com.ferreteriamapocho.data

import com.example.ferreteriamapocho.data.Product
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

object CartManager {

    private val _cartItems = MutableStateFlow<List<CartItem>>(emptyList())
    val cartItems: StateFlow<List<CartItem>> = _cartItems

    fun addToCart(product: Product) {
        val current = _cartItems.value.toMutableList()
        val index = current.indexOfFirst { it.product.id == product.id }

        if (index >= 0) {
            val item = current[index]
            if (item.quantity < product.stock) {
                current[index] = item.copy(quantity = item.quantity + 1)
            }
        } else {
            current.add(CartItem(product, 1))
        }
        _cartItems.value = current
    }

    fun removeOne(product: Product) {
        val current = _cartItems.value.toMutableList()
        val index = current.indexOfFirst { it.product.id == product.id }

        if (index >= 0) {
            val item = current[index]
            if (item.quantity > 1) {
                current[index] = item.copy(quantity = item.quantity - 1)
            } else {
                current.removeAt(index)
            }
        }
        _cartItems.value = current
    }

    fun removeAll(product: Product) {
        _cartItems.value = _cartItems.value.filter { it.product.id != product.id }
    }

    fun clear() {
        _cartItems.value = emptyList()
    }
}

data class CartItem(
    val product: Product,
    val quantity: Int
)
