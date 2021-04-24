package com.okbit.ubook.crud

import android.content.Intent
import android.nfc.Tag
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.google.android.gms.tasks.Task
import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.FirebaseFirestore
import com.okbit.ubook.databinding.ActivityMainBinding
import com.okbit.ubook.databinding.ActivityMainBookCrudBinding

class MainBookCrud : AppCompatActivity() {

    private val db = FirebaseFirestore.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBookCrudBinding.inflate(layoutInflater)
        setContentView(binding.root)


        binding.buttonDelete.setOnClickListener {
            openList()
        }



        binding.buttonSave.setOnClickListener {
            saveBook()
        }

        binding.buttonGet.setOnClickListener{
            getAllBook()
        }

    }

    private fun openList() {
        val intent = Intent(this, BookList::class.java)
        startActivity(intent)
    }

    private fun saveBook() {
        val book = hashMapOf(
            "title" to "",
            "condition" to "",
            "cover" to "",
            "author" to "",
            "category" to "",
            "description" to "",
            "contact" to "",
            "price" to "",
            "isbn" to "",
            "language" to "",
            "delivery" to "",
        )
        db.collection("test").add(book)
    }

    private fun getAllBook() {
        db.collection("books").get().addOnSuccessListener {
            result -> for (libro in result){
                Log.d("datos doc: ", "$libro.id ${libro.data}")
            }
        }
    }

}

