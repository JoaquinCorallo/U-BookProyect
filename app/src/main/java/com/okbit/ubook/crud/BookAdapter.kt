package com.okbit.ubook.crud

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.okbit.ubook.databinding.ViewBookItemBinding

class BookAdapter(private val books: List<Book>,
                  private val movieClickListener: (Book) -> Unit) : RecyclerView.Adapter<BookAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ViewBookItemBinding.inflate(
            LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val book = books[position]
        holder.bind(book)
        holder.itemView.setOnClickListener { movieClickListener(book) }
    }

    override fun getItemCount() = books.size

    class  ViewHolder(private val binding: ViewBookItemBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(book: Book){
            binding.titleBook.text = book.title
            Glide.with(binding.root.context).load(book.cover).into(binding.cover)

        }
    }
}