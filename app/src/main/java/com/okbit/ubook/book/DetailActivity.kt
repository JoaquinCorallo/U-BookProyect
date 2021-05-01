package com.okbit.ubook.book

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import com.okbit.ubook.R

class DetailActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        val bookfs: BookModel? = intent.getParcelableExtra("BOOKS_DETAIL_DATA")
        findViewById<TextView>(R.id.sports_title_text).text = bookfs?.title
        findViewById<TextView>(R.id.sports_originated_text).text = bookfs?.author
    }
}