package com.okbit.ubook.api

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object BookDbClient {

    private val retrofit = Retrofit.Builder()
            .baseUrl("https://firestore.googleapis.com/v1/projects/ubook-8d856/databases/(default)/documents/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

    val service = retrofit.create(BookApiService::class.java)

}