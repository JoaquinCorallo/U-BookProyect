package com.okbit.ubook.crud

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import androidx.core.text.bold
import androidx.core.text.buildSpannedString
import com.bumptech.glide.Glide
import com.okbit.ubook.databinding.ActivityDetailBookBinding
import com.okbit.ubook.find.MapsBooksActivity

class DetailBookActivity : AppCompatActivity() {

    companion object {
        const val EXTRA_BOOK = "DetailBookActivity:book"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityDetailBookBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val book = intent.getParcelableExtra<Book>(EXTRA_BOOK)
        if (book != null) {
            title = book.title
            Glide
                .with(this)
                .load(book.cover)
                .into(binding.backdrop)
            binding.autordetail.text = book.author
            binding.categoriadetail.text = book.category
            binding.condiciondetail.text = book.condition
            binding.titledetail.text = book.title
            binding.descripciondetail.text = book.description
            bindingInfoDetail(binding.infodetail, book)
        }

        binding.myHomeButton.setOnClickListener {
            openMapBook()
        }


    }

    private fun bindingInfoDetail(infodetail: TextView, book: Book) {
        infodetail.text = buildSpannedString {
            bold { append("Idioma: ") }
            appendLine(book.language)

            bold { append("Contacto: ") }
            appendLine(book.contact)

            bold { append("Zona de entrega: ") }
            appendLine(book.delivery)
        }

    }

    private fun openMapBook() {
        val intent = Intent(this, MapsBooksActivity::class.java)
        startActivity(intent)
    }
}