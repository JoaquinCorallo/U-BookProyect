package com.okbit.ubook.api

import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.http.GET
import retrofit2.http.Query


interface BookApiService {
    @GET("books")
    fun getAllBooks(): Call<BookDbResult>
}

