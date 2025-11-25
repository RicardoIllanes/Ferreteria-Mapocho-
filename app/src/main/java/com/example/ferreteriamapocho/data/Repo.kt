package com.example.ferreteriamapocho.data

import kotlin.collections.find

object Repo {

    val products = listOf(
        Product(
            id = 1,
            name = "Martillo Stanley 16 oz",
            sku = "FER-001",
            precio = 9990.0,
            stock = 25,
            imageUrl = "https://i.imgur.com/LmX8LUy.jpeg",
            category = "Herramientas"
        ),
        Product(
            id = 2,
            name = "Taladro Black & Decker 12V",
            sku = "FER-002",
            precio = 45990.0,
            stock = 15,
            imageUrl = "https://i.imgur.com/BNq4dzv.jpeg",
            category = "Electricidad"
        ),
        Product(
            id = 3,
            name = "Caja de clavos 2\" (500 unidades)",
            sku = "FER-003",
            precio = 3990.0,
            stock = 50,
            imageUrl = "https://i.imgur.com/ylxYqzE.jpeg",
            category = "Construcción"
        ),
        Product(
            id = 4,
            name = "Cinta métrica 5m Truper",
            sku = "FER-004",
            precio = 2990.0,
            stock = 30,
            imageUrl = "https://i.imgur.com/QLUIZVD.jpeg",
            category = "Medición"
        ),
        Product(
            id = 5,
            name = "Destornillador Philips Stanley",
            sku = "FER-005",
            precio = 2490.0,
            stock = 40,
            imageUrl = "https://i.imgur.com/3zVwbTI.jpeg",
            category = "Herramientas"
        ),

        Product(
            id = 6,
            name = "Alicate Universal 8'' Truper",
            sku = "FER-006",
            precio = 5490.0,
            stock = 22,
            imageUrl = "https://i.imgur.com/TulD9mW.jpeg",
            category = "Herramientas"
        ),
        Product(
            id = 7,
            name = "Serrucho Profesional 18'' Stanley",
            sku = "FER-007",
            precio = 7990.0,
            stock = 18,
            imageUrl = "https://i.imgur.com/HUXhZ0E.jpeg",
            category = "Corte"
        ),
        Product(
            id = 8,
            name = "Llave Francesa 10'' Truper",
            sku = "FER-008",
            precio = 6990.0,
            stock = 25,
            imageUrl = "https://i.imgur.com/L0nFYe0.jpeg",
            category = "Herramientas"
        ),
        Product(
            id = 9,
            name = "Pistola de Silicona 40W",
            sku = "FER-009",
            precio = 3990.0,
            stock = 40,
            imageUrl = "https://i.imgur.com/KG8YkY1.jpeg",
            category = "Construcción"
        ),
        Product(
            id = 10,
            name = "Juego de Brocas para Metal (13 pcs)",
            sku = "FER-010",
            precio = 5990.0,
            stock = 30,
            imageUrl = "https://i.imgur.com/ZIpIyGo.jpeg",
            category = "Electricidad"
        ),

        Product(
            id = 11,
            name = "Nivel de Burbuja 12'' Pretul",
            sku = "FER-011",
            precio = 3490.0,
            stock = 20,
            imageUrl = "https://i.imgur.com/7UuXkjg.jpeg",
            category = "Medición"
        ),
        Product(
            id = 12,
            name = "Guantes de Seguridad Anticorte",
            sku = "FER-012",
            precio = 2990.0,
            stock = 70,
            imageUrl = "https://i.imgur.com/tv5qn4i.jpeg",
            category = "Seguridad"
        ),
        Product(
            id = 13,
            name = "Linterna LED Recargable",
            sku = "FER-013",
            precio = 8990.0,
            stock = 15,
            imageUrl = "https://i.imgur.com/nLr7cWg.jpeg",
            category = "Iluminación"
        ),
        Product(
            id = 14,
            name = "Juego de Destornilladores (6 piezas)",
            sku = "FER-014",
            precio = 4990.0,
            stock = 50,
            imageUrl = "https://i.imgur.com/MuQkGIQ.jpeg",
            category = "Herramientas"
        ),
        Product(
            id = 15,
            name = "Candado de Acero 40mm",
            sku = "FER-015",
            precio = 2490.0,
            stock = 60,
            imageUrl = "https://i.imgur.com/WBcsDxX.jpeg",
            category = "Seguridad"
        ),

        Product(
            id = 16,
            name = "Flexómetro 8m Stanley",
            sku = "FER-016",
            precio = 5990.0,
            stock = 35,
            imageUrl = "https://i.imgur.com/ylxYqzE.jpeg",
            category = "Medición"
        ),
        Product(
            id = 17,
            name = "Mascarilla Respiratoria 3M",
            sku = "FER-017",
            precio = 3490.0,
            stock = 80,
            imageUrl = "https://i.imgur.com/HUXhZ0E.jpeg",
            category = "Seguridad"
        ),
        Product(
            id = 18,
            name = "Llave Ajustable 12'' Truper",
            sku = "FER-018",
            precio = 8990.0,
            stock = 10,
            imageUrl = "https://i.imgur.com/BNq4dzv.jpeg",
            category = "Herramientas"
        ),
        Product(
            id = 19,
            name = "Caja de Herramientas 19'' Pretul",
            sku = "FER-019",
            precio = 12990.0,
            stock = 12,
            imageUrl = "https://i.imgur.com/LmX8LUy.jpeg",
            category = "Herramientas"
        ),
        Product(
            id = 20,
            name = "Cúter Profesional con 5 Hojas",
            sku = "FER-020",
            precio = 1990.0,
            stock = 100,
            imageUrl = "https://i.imgur.com/3zVwbTI.jpeg",
            category = "Corte"
        )
    )




    fun productById(id: Int): Product? = products.find { it.id == id }
}