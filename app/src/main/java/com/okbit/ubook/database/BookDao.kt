package com.okbit.ubook.database

import androidx.room.*
import com.okbit.ubook.crud.Book

@Dao
interface BookDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(bkList: Book)

    @Query("SELECT * FROM books ORDER BY id DESC")
    fun getBooks(): MutableList<Book>

    @Query("SELECT * FROM books WHERE condition = :cond ORDER BY id DESC")
    fun getBooksWithCategory(cond: String): MutableList<Book>

    @Update
    fun updateBk(vararg bk: Book)

    @Delete
    fun deleteBk(vararg  bk: Book)
}