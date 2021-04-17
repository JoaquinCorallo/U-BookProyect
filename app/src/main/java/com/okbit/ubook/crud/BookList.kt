package com.okbit.ubook.crud

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils.isEmpty
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.okbit.ubook.databinding.ActivityBookListBinding

class BookList : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityBookListBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.recycler.adapter = BookAdapter(
           listOf(
                Book(1,"Book 1", "https://ubookweb.pythonanywhere.com/media/libro/L1.webp","","","","Intercambio","",1.99, 1, "", "" ),
                Book(2, "Book 2", "https://ubookweb.pythonanywhere.com/media/libro/L2.webp","","","","Intercambio","",1.99, 2, "", "" ),
                Book(3, "Book 3", "https://ubookweb.pythonanywhere.com/media/libro/L3.webp","","","","Venta","",1.99, 3, "", "" ),
                Book(4, "Book 4", "https://ubookweb.pythonanywhere.com/media/libro/L4.webp","","","","Venta","",1.99, 4, "", "" ),
                Book(5, "Book 5", "https://ubookweb.pythonanywhere.com/media/libro/L5.webp","","","","Donación","",1.99, 5, "", "" ),
                Book(6, "Book 6", "https://ubookweb.pythonanywhere.com/media/libro/L6.webp","","","","Donación","",1.99, 6, "", "" ),
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