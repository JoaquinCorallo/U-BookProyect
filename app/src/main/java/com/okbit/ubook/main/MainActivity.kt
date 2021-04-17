package com.okbit.ubook.main

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatDelegate
import androidx.appcompat.widget.SwitchCompat
import com.google.firebase.analytics.FirebaseAnalytics
import com.google.firebase.analytics.ktx.analytics
import com.google.firebase.ktx.Firebase
import com.okbit.ubook.R
import com.okbit.ubook.Upload.UploadActivity
import com.okbit.ubook.crud.BookList
import com.okbit.ubook.crud.MainBookCrud
import com.okbit.ubook.databinding.ActivityMainBinding
import java.util.*

class MainActivity : AppCompatActivity() {
    //private lateinit var firebaseAnalytics: FirebaseAnalytics
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // Firebase eventos
        val analytics = FirebaseAnalytics.getInstance(this)
        val bundle = Bundle()
        bundle.putString("message", "Integracion Firebase completa")
        analytics.logEvent("InitScreen", bundle)
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

        binding.crudBtn.setOnClickListener {
            openCrudBook()
        }
    }

    private fun openBookList() {
        val intent = Intent(this, BookList::class.java)
        startActivity(intent)
    }

    private fun openCrudBook() {
        val intent = Intent(this, MainBookCrud::class.java)
        startActivity(intent)
    }
}