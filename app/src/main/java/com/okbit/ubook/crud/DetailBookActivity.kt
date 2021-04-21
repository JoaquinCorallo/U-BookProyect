package com.okbit.ubook.crud

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.okbit.ubook.databinding.ActivityDetailBookBinding

class DetailBookActivity : AppCompatActivity() {

    companion object {
        const val EXTRA_TITLE = "DetailBookActivity:title"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityDetailBookBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val title = intent.getStringExtra(EXTRA_TITLE)
        binding.titledetail.text = title
    }
}