package com.okbit.ubook.crud

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.okbit.ubook.databinding.ActivityBookListBinding

class BookList : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityBookListBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.recycler.adapter = BookAdapter(
            listOf(
                Book("Book 1", "https://ubookweb.pythonanywhere.com/media/libro/L1.webp","","","","","", ""),
                Book("Book 2", "https://ubookweb.pythonanywhere.com/media/libro/L2.webp","","","","","","" ),
                Book("Book 3", "https://ubookweb.pythonanywhere.com/media/libro/L3.webp","","","","","","" ),
                Book("Book 4", "https://ubookweb.pythonanywhere.com/media/libro/L4.webp","","","","","","" ),
                Book("Book 5", "https://ubookweb.pythonanywhere.com/media/libro/L5.webp","","","","","","" ),
                Book("Book 6", "https://ubookweb.pythonanywhere.com/media/libro/L6.webp","","","","","","" ),
            )
        ) { book ->
            Toast.makeText(this@BookList, book.title, Toast.LENGTH_LONG).show()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d("BookList", "onDestroy")
    }
}