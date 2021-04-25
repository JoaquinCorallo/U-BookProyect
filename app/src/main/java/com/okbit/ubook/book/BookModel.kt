package com.okbit.ubook.book

data class BookModel (
    val id: Int,
    val title: String,
    val cover: String,
    val author: String,
    val category: String,
    val description: String,
    val condition: String,
    val contact: String,
    val price: Double,
    val isbn: Int,
    val language: String,
    val delivery: String,
    )