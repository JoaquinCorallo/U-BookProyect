package com.okbit.ubook.crud

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Book(
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
    val delivery: String
    ) : Parcelable
