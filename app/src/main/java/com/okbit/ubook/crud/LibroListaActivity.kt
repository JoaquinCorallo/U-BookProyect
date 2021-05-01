package com.okbit.ubook.crud

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.toObject
import com.okbit.ubook.R

class LibroListaActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_libro_lista)

        val doclist = FirebaseFirestore.getInstance()

        doclist.collection("books")
            .get()
            .addOnCompleteListener() { task ->
                if (task.isSuccessful()) {
                    val sumary = ArrayList<Book>()
                    for (document in task.result!!){
                        sumary.add(document.toObject(Book::class.java))
                    }


                }

            }
    }
}