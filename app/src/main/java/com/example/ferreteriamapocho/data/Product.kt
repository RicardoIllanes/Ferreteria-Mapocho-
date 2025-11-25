package com.example.ferreteriamapocho.data 

data class Product(
    val id: Int,
    val name: String,
    val sku: String,
    val precio: Double,
    var stock :Int,
    val imageUrl: String,
    val category: String
)