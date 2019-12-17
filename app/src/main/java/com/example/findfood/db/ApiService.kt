package com.example.findfood.db

import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    //パラメータ指定
    @GET("hotpepper/gourmet/v1")
    //多分モデルを引き出してる
    suspend fun apiDemo(
        @Query("key") key: String,
        @Query("large_area") largeArea: String,
        @Query("count") count: Int,
        @Query("format") format: String
    ): FoodResponse
}
