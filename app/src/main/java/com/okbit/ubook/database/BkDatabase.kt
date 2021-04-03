package com.okbit.ubook.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.okbit.ubook.crud.Book
import java.security.AccessControlContext

@Database(entities = [Book::class], version = 1)
abstract class BkDatabase: RoomDatabase() {
    abstract val bookDao: BookDao
}

private lateinit var INSTANCE: BkDatabase

fun getDatabase(context: Context): BkDatabase {
    synchronized(BkDatabase::class.java) {
        if (!::INSTANCE.isInitialized) {
            INSTANCE = Room.databaseBuilder(context.applicationContext, BkDatabase::class.java,
            "books_db"
            ).build()
        }
        return INSTANCE
    }
}