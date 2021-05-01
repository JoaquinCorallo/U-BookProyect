package com.okbit.ubook.firestore

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Query
import com.okbit.ubook.R


class MainFirestoreActivity : AppCompatActivity(), BooksAdapter.BooksAdapterListener {

    private lateinit var adapter: BooksAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_firestore)

        title = "Listado de Libros"

        val query: Query = FirebaseFirestore.getInstance().collection("books")

        val recyclerView: RecyclerView = findViewById(R.id.books_list)
        adapter = BooksAdapter(query, this)
        recyclerView.adapter = adapter
        adapter.stateRestorationPolicy = RecyclerView.Adapter.StateRestorationPolicy.PREVENT_WHEN_EMPTY
    }

    override fun onStart() {
        super.onStart()
        adapter.startListening()
    }

    override fun onStop() {
        super.onStop()
        adapter.startListening()
    }

    override fun onBookSelected(books: Books?) {
        val intent = Intent(applicationContext, DetailFirestoreActivity::class.java)
        intent.putExtra("BOOKS_DETAIL_DATA", books)
        startActivity(intent)
    }
}