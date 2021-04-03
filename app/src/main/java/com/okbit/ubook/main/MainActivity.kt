package com.okbit.ubook.main

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.okbit.ubook.R
import com.okbit.ubook.crud.BookList
import com.okbit.ubook.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.listBtn.setOnClickListener {
            openBookList()
        }
    }

    private fun openBookList() {
        val intent = Intent(this, BookList::class.java)
        startActivity(intent)
    }
}