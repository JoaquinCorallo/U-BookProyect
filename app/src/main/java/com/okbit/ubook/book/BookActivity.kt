package com.okbit.ubook.book

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.Adapter.StateRestorationPolicy
import androidx.recyclerview.widget.RecyclerView.Adapter.StateRestorationPolicy.*
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Query
import com.okbit.ubook.R


class BookActivity : AppCompatActivity(), BookListAdapter.BookListAdapterListener {

    private lateinit var adapter: BookListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val query: Query = FirebaseFirestore.getInstance().collection("books")

        val recyclerView: RecyclerView = findViewById(R.id.firestoreList)
        adapter = BookListAdapter(query, this)
        recyclerView.adapter = adapter
        adapter.stateRestorationPolicy = PREVENT_WHEN_EMPTY
    }

    override fun onStart() {
        super.onStart()
        adapter.startListening()
    }

    override fun onStop() {
        super.onStop()
        adapter.startListening()
    }

    override fun onSportSelected(bookfs: BookModel?) {
        val intent = Intent(applicationContext, DetailActivity::class.java)
        intent.putExtra("BOOKS_DETAIL_DATA", bookfs)
        startActivity(intent)
    }
}