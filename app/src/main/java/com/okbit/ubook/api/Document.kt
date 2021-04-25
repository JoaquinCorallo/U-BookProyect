package com.okbit.ubook.api

data class Document(
    val createTime: String,
    val fields: Fields,
    val name: String,
    val updateTime: String
)