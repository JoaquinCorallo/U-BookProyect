package com.okbit.ubook.book

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.okbit.ubook.R

private  const val  TAG: String = "HOMEPAGE_LOG"

class BookActivity : AppCompatActivity() {

    private  val firebaseRepo: FirebaseRepo = FirebaseRepo()

    private  var bookList: List<BookModel> = ArrayList()
    private val bookListAdapter: BookListAdapter = BookListAdapter(bookList)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_book)

        //check user
        if (firebaseRepo.getUser() == null){
            //create new user
            firebaseRepo.createUser().addOnCompleteListener{
                if (it.isSuccessful){
                    //load data
                    loadBookData()

                } else {
                    Log.d(TAG, "Error: ${it.exception!!.message}")
                }
            }
        } else {
            //user logged in
            loadBookData()
        }
        // init recycler view
        var firestore = findViewById<RecyclerView>(R.id.firestoreList)
        firestore.layoutManager = LinearLayoutManager(this)
        firestore.adapter = bookListAdapter


    }

    private fun loadBookData() {
        firebaseRepo.getBookList().addOnCompleteListener{
            if(it.isSuccessful){
                bookList = it.result!!.toObjects(BookModel::class.java)
                bookListAdapter.bookListItems = bookList
                bookListAdapter.notifyDataSetChanged()


            } else {
                Log.d(TAG, "Error: ${it.exception!!.message}")
            }
        }
    }
}