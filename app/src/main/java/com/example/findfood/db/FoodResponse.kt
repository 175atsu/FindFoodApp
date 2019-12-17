package com.example.findfood.db

data class FoodResponse(
    val results: Result
)

data class Result(
    val shop: List<Shop>
)

data class Shop(
    val id: String?,
    val name: String?,
    val logo_image: String?,
    val address: String?,
    val lat: Float?,
    val lng: Float?,
    val genre: Genre,
    val budget: Budget?,
    val lunch: String?,
    val urls: Pc?
)

data class Genre(
    val code: String?
)

data class Budget(
    val code: String?
)

data class Pc(
    val pc: String?
)
