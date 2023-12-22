package com.example.mobilesoftware_project

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface RetrofitInterface {
    @GET("exchangeJSON")
    fun doGetResult(
        @Query("authkey") key: String,
        @Query("searchdate") searchdate: String,
        @Query("data") data: String
    ): Call<List<ClassExchange>>
}