package com.okbit.ubook.api

data class Fields(
    val author: Author,
    val category: Category,
    val condition: Condition,
    val contact: Contact,
    val cover: Cover,
    val delivery: Delivery,
    val description: Description,
    val isbn: Isbn,
    val language: Language,
    val price: Price,
    val title: Title
)