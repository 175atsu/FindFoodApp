package com.example.findfood.db

import java.util.*
import kotlin.collections.ArrayList

//public class FoodResponse{
//    val shop: ArrayList<Shop>? = null
//}

//data class FoodResponse(
//    val shop: MutableList<Shop>?
//)
//data class Shop(
//    val id: String?,
//    val name: String?,
//    val lunch: String?
//)

data class FoodResponse(
    val results: Result
)

data class Result(
    val shop: List<Shop>
)

data class Shop(
    val id: String?,
    val name: String?,
    val log_image: String?,
    val address: String?,
    val lat: Float?,
    val lng: Float?,
    val genre: Genre,
    val budget: Budget?,
    val lunch: String?
)

data class Genre(
    val code: String?
)

data class Budget(
    val code: String?
)
