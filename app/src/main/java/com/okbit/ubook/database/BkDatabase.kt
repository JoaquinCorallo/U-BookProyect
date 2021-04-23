package com.okbit.ubook.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.okbit.ubook.crud.Book
import java.security.AccessControlContext

/*@Database(entities = [Book::class], version = 1)
abstract class BkDatabase: RoomDatabase() {
    abstract fun bookDao(): BookDao?

    companion object {
        private var INSTANCE: BkDatabase?= null

        fun getAppDatabase(context: Context): BkDatabase? {

            if(INSTANCE == null ) {

                INSTANCE = Room.databaseBuilder<BkDatabase>(
                    context.applicationContext, BkDatabase::class.java, "BookDB"
                )
                    .allowMainThreadQueries()
                    .build()

            }
            return INSTANCE
        }
        fun destroyInstance() {
            INSTANCE = null
        }
    }
}*/