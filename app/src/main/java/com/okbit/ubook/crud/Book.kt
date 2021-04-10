package com.okbit.ubook.crud

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "books")
data class Book(@PrimaryKey val id: Int,
                val title: String,
                val cover: String, //base64
                val author: String,
                val category: String,
                val description: String,
                val condition: String,
                val contact: String,
                val price: Double,
                val isbn: Int,
                val language: String,
                val delivery: String)
