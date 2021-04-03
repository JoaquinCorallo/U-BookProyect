package com.okbit.ubook.main.main

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.appcompat.widget.SwitchCompat
import com.okbit.ubook.R
import com.okbit.ubook.main.upload.UploadActivity
import java.text.DecimalFormatSymbols
import java.util.*


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val locale = Locale("es", "es")
        Locale.setDefault(locale)

        val button = findViewById<Button>(R.id.btnupload)
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
    }
}