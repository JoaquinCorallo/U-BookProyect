package com.okbit.ubook.crud

import android.content.Intent
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
                Book(1,"Book 1", "https://ubookweb.pythonanywhere.com/media/libro/L1.webp","Hugo Quintero","Drama","Lorem Ipsum es simplemente el texto de relleno de las imprentas y archivos de texto. Lorem Ipsum ha sido el texto de relleno estándar de las industrias desde el año 1500, cuando un impresor (N. del T. persona que se dedica a la imprenta) desconocido usó una galería de textos y los mezcló de tal manera que logró hacer un libro de textos especimen.","Intercambio","email@email.com",1.99, 1, "es", "Zona 1" ),
                Book(2, "Book 2", "https://ubookweb.pythonanywhere.com/media/libro/L2.webp","Hugo Quintero","Drama","Lorem Ipsum es simplemente el texto de relleno de las imprentas y archivos de texto. Lorem Ipsum ha sido el texto de relleno estándar de las industrias desde el año 1500, cuando un impresor (N. del T. persona que se dedica a la imprenta) desconocido usó una galería de textos y los mezcló de tal manera que logró hacer un libro de textos especimen.","Intercambio","email@email.com",1.99, 2, "es", "Zona 1" ),
                Book(3, "Book 3", "https://ubookweb.pythonanywhere.com/media/libro/L3.webp","Hugo Quintero","Drama","Lorem Ipsum es simplemente el texto de relleno de las imprentas y archivos de texto. Lorem Ipsum ha sido el texto de relleno estándar de las industrias desde el año 1500, cuando un impresor (N. del T. persona que se dedica a la imprenta) desconocido usó una galería de textos y los mezcló de tal manera que logró hacer un libro de textos especimen.","Venta","email@email.com",1.99, 3, "es", "Zona 2" ),
                Book(4, "Book 4", "https://ubookweb.pythonanywhere.com/media/libro/L4.webp","Hugo Quintero","Drama","Lorem Ipsum es simplemente el texto de relleno de las imprentas y archivos de texto. Lorem Ipsum ha sido el texto de relleno estándar de las industrias desde el año 1500, cuando un impresor (N. del T. persona que se dedica a la imprenta) desconocido usó una galería de textos y los mezcló de tal manera que logró hacer un libro de textos especimen.","Venta","email@email.com",1.99, 4, "es", "Zona 3" ),
                Book(5, "Book 5", "https://ubookweb.pythonanywhere.com/media/libro/L5.webp","Hugo Quintero","Drama","Lorem Ipsum es simplemente el texto de relleno de las imprentas y archivos de texto. Lorem Ipsum ha sido el texto de relleno estándar de las industrias desde el año 1500, cuando un impresor (N. del T. persona que se dedica a la imprenta) desconocido usó una galería de textos y los mezcló de tal manera que logró hacer un libro de textos especimen.","Donación","email@email.com",1.99, 5, "es", "Zona 4" ),
                Book(6, "Book 6", "https://ubookweb.pythonanywhere.com/media/libro/L6.webp","Hugo Quintero","Drama","Lorem Ipsum es simplemente el texto de relleno de las imprentas y archivos de texto. Lorem Ipsum ha sido el texto de relleno estándar de las industrias desde el año 1500, cuando un impresor (N. del T. persona que se dedica a la imprenta) desconocido usó una galería de textos y los mezcló de tal manera que logró hacer un libro de textos especimen.","Donación","email@email.com",1.99, 6, "es", "Zona 5" ),
            )
        ) { book ->
            navigateTo(book)
        }

    }

    private fun navigateTo(book: Book) {
        val intent = Intent(this, DetailBookActivity::class.java)
        intent.putExtra(DetailBookActivity.EXTRA_BOOK, book)
        startActivity(intent)
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d("BookList", "onDestroy")
    }
}