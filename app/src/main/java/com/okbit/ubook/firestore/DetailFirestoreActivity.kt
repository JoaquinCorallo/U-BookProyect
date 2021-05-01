package com.okbit.ubook.firestore

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.okbit.ubook.R
import com.okbit.ubook.find.MapsBooksActivity

class DetailFirestoreActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_firestore)



        val books: Books? = intent.getParcelableExtra("BOOKS_DETAIL_DATA")
        title = books?.title
        val portada = findViewById<ImageView>(R.id.detailcover)
        Glide.with(this).load(books?.cover).into(portada)
        findViewById<TextView>(R.id.detailtitle).text = books?.title
        findViewById<TextView>(R.id.detailauthor).text = books?.author
        findViewById<TextView>(R.id.detailcategory).text = books?.category
        findViewById<TextView>(R.id.detailcondition).text = books?.condition
        findViewById<TextView>(R.id.detaildescription).text = books?.description
        findViewById<TextView>(R.id.detaillanguage).text = books?.language
        findViewById<TextView>(R.id.detailcontact).text = books?.contact
        findViewById<TextView>(R.id.detaildelivery).text = books?.delivery


        val botonMap = findViewById<FloatingActionButton>(R.id.detail_home_button)
        botonMap.setOnClickListener{
            openMapBook()
        }
    }



    private fun openMapBook() {
        val intent = Intent(this, MapsBooksActivity::class.java)
        startActivity(intent)
    }
}