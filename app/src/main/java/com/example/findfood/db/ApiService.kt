package com.example.findfood.db

import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    //パラメータ指定
    @GET("hotpepper/gourmet/v1")
    //多分モデルを引き出してる
    suspend fun apiDemo(
        @Query("key") key: Int,
        @Query("large_area") largeArea: String,
        @Query("format") format: Int
    ): List<FoodResponse>
}
