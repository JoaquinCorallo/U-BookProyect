package com.okbit.ubook.database

import androidx.room.*
import com.okbit.ubook.crud.Book

@Dao
interface BookDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(bkList: MutableList<Book>)

    @Query("SELECT * FROM books")
    fun getBooks(): MutableList<Book>

    @Query("SELECT * FROM books WHERE category = :cat")
    fun getBooksWithCategory(cat: String): MutableList<Book>

    @Update
    fun updateBk(vararg bk: Book)

    @Delete
    fun deleteBk(vararg  bk: Book)
}