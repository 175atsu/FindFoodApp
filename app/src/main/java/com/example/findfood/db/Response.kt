package com.example.findfood.db

data class FoodResponse(
    val shop: Shop
)

data class Shop(
    val id: String?,
    val name: String?,
    val log_image: String?,
    val address: String?,
    val lat: Float?,
    val lng: Float?,
    val genre: Genre,
    val budget: Budget,
    val lunch: String?
)

data class Genre(
    val code: String?
)

data class Budget(
    val code: String?
)
