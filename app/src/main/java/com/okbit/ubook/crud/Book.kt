package com.okbit.ubook.crud

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "books")
data class Book(@PrimaryKey val title: String,
                val cover: String,
                val author: String,
                val category: String,
                val description: String,
                val condition: String,
                val contact: String,
                val delivery: String)