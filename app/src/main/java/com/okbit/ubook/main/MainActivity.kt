package com.okbit.ubook.main

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatDelegate
import androidx.appcompat.widget.SwitchCompat
import com.okbit.ubook.R
import com.okbit.ubook.Upload.UploadActivity
import com.okbit.ubook.crud.BookList
import com.okbit.ubook.databinding.ActivityMainBinding
import java.util.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val locale = Locale("es", "es")
        Locale.setDefault(locale)

        val button = findViewById<Button>(R.id.btn_upload)
        button.setOnClickListener {
            val intent = Intent(this, UploadActivity::class.java)
            startActivity(intent)
        }

        val switchchangetheme = findViewById<SwitchCompat>(R.id.switch_change_theme)

        switchchangetheme.setTextOn("On")
        switchchangetheme.setTextOff("Off")
        switchchangetheme?.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked){
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
                delegate.applyDayNight()
            } else {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
                delegate.applyDayNight()

            }
        }

        binding.listBtn.setOnClickListener {
            openBookList()
        }
    }

    private fun openBookList() {
        val intent = Intent(this, BookList::class.java)
        startActivity(intent)
    }
}