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
            imageUrl = "https://firebasestorage.googleapis.com/v0/b/ferreteria-d3107.firebasestorage.app/o/products%2Fmartillo.png?alt=media&token=ba507dbc-51f1-41c0-8600-720e9eff7a78",
            category = "Herramientas"
        ),
        Product(
            id = 2,
            name = "Taladro Black & Decker 12V",
            sku = "FER-002",
            precio = 45990.0,
            stock = 15,
            imageUrl = "https://firebasestorage.googleapis.com/v0/b/ferreteria-d3107.firebasestorage.app/o/products%2Ftaladro.png?alt=media&token=3c8f91f7-9038-4c4e-a77f-78618a2bc98a",
            category = "Electricidad"
        ),
        Product(
            id = 3,
            name = "Caja de clavos 2\" (500 unidades)",
            sku = "FER-003",
            precio = 3990.0,
            stock = 50,
            imageUrl = "https://firebasestorage.googleapis.com/v0/b/ferreteria-d3107.firebasestorage.app/o/products%2Fclavos.png?alt=media&token=cd069b7b-7bc6-4e3c-84e6-c1bdfc2c8f39",
            category = "Construcción"
        ),
        Product(
            id = 4,
            name = "Cinta métrica 5m Truper",
            sku = "FER-004",
            precio = 2990.0,
            stock = 30,
            imageUrl = "https://firebasestorage.googleapis.com/v0/b/ferreteria-d3107.firebasestorage.app/o/products%2Fcinta%20metrica.png?alt=media&token=bf673c26-24db-4775-b875-21a5a01285c4",
            category = "Medición"
        ),
        Product(
            id = 5,
            name = "Destornillador Philips Stanley",
            sku = "FER-005",
            precio = 2490.0,
            stock = 40,
            imageUrl = "https://firebasestorage.googleapis.com/v0/b/ferreteria-d3107.firebasestorage.app/o/products%2Fdestornillador.png?alt=media&token=a4d52cc3-bb6b-462b-97c0-d21399217f9b",
            category = "Herramientas"
        ),

        Product(
            id = 6,
            name = "Alicate Universal 8'' Truper",
            sku = "FER-006",
            precio = 5490.0,
            stock = 22,
            imageUrl = "https://firebasestorage.googleapis.com/v0/b/ferreteria-d3107.firebasestorage.app/o/products%2FAlicate%20universal.webp?alt=media&token=c02d0ada-c4bf-4d45-8ae0-ece0c851224d",
            category = "Herramientas"
        ),
        Product(
            id = 7,
            name = "Serrucho Profesional 18'' Stanley",
            sku = "FER-007",
            precio = 7990.0,
            stock = 18,
            imageUrl = "https://firebasestorage.googleapis.com/v0/b/ferreteria-d3107.firebasestorage.app/o/products%2FSerrucho.webp?alt=media&token=b8064619-74db-4719-aaa8-2abcf8e2243d",
            category = "Corte"
        ),
        Product(
            id = 8,
            name = "Llave Francesa 10'' Truper",
            sku = "FER-008",
            precio = 6990.0,
            stock = 25,
            imageUrl = "https://firebasestorage.googleapis.com/v0/b/ferreteria-d3107.firebasestorage.app/o/products%2Fllave%20francesa.png?alt=media&token=3b68d1db-717f-4bf9-be5b-f563c2049951",
            category = "Herramientas"
        ),
        Product(
            id = 9,
            name = "Pistola de Silicona 40W",
            sku = "FER-009",
            precio = 3990.0,
            stock = 40,
            imageUrl = "https://firebasestorage.googleapis.com/v0/b/ferreteria-d3107.firebasestorage.app/o/products%2Fpistola%20silicona.webp?alt=media&token=11b63429-3ecd-4b49-b41f-58ab10ea1671",
            category = "Construcción"
        ),
        Product(
            id = 10,
            name = "Juego de Brocas para Metal (13 pcs)",
            sku = "FER-010",
            precio = 5990.0,
            stock = 30,
            imageUrl = "https://firebasestorage.googleapis.com/v0/b/ferreteria-d3107.firebasestorage.app/o/products%2FJuegos%20de%20brocas.webp?alt=media&token=82f7c4a7-66a8-4e4e-972e-106b2ff6dcc6",
            category = "Electricidad"
        ),
    )




    fun productById(id: Int): Product? = products.find { it.id == id }
}