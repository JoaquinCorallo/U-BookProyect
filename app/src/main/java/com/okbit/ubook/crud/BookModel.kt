package com.okbit.ubook.crud

class BookModel {
    // variables for storing our image and name.
    private var id: Int? = null
    private var title: String? = null
    private var cover: String? = null
    private var author: String? = null
    private var category: String? = null
    private var description: String? = null
    private var condition: String? = null
    private var contact: String? = null
    private var price: Double? = null
    private var isbn: Int? = null
    private var language: String? = null
    private var delivery: String? = null

    fun BookModel() {
        // empty constructor required for firebase.
    }

    // constructor for our object class.
    fun BookModel(name: String?, imgUrl: String?) {
        this.id = id
        this.title = title
        this.cover = cover
        this.author = author
        this.category = category
        this.description = description
        this.condition = condition
        this.contact = contact
        this.price = price
        this.isbn = isbn
        this.language = language
        this.delivery = delivery
    }

    // getter and setter methods
    fun getTitle(): String? {
        return title
    }

    fun setTitle(title: String?) {
        this.title = title
    }

    fun getAuthor(): String? {
        return author
    }

    fun setAuthor(author: String?) {
        this.author = author
    }
}