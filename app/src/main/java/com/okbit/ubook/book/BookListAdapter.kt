package com.okbit.ubook.book

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.view.menu.ActionMenuItemView
import androidx.recyclerview.widget.RecyclerView
import com.okbit.ubook.R


private const val POST_TYPE_DESC: Int = 0
private const val POST_TYPE_IMAGE: Int = 1

class BookListAdapter(var bookListItems: List<BookModel>): RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    //view holders for all types of items
    class  DescViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        fun bind(bookModel: BookModel){
            //descBookTitle.text = bookModel.title
            //descBookAuthor.text = bookModel.author

        }

    }

    class  ImageViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        fun bind(bookModel: BookModel){
            //itemView.imageBookTitle.text = bookModel.title
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        if (viewType == POST_TYPE_DESC) {
            val view = LayoutInflater.from(parent.context).inflate(R.layout.item_with_desc, parent, false)
            return DescViewHolder(view)
        } else {
            val view = LayoutInflater.from(parent.context).inflate(R.layout.item_with_image, parent, false)
            return ImageViewHolder(view)
        }
    }

    override fun getItemCount(): Int {
        return bookListItems.size
    }

    override fun getItemViewType(position: Int): Int {
        return if (bookListItems[position].id == 0) {
            POST_TYPE_DESC
        } else {
            POST_TYPE_IMAGE
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (getItemViewType(position) == POST_TYPE_DESC){
            (holder as DescViewHolder).bind(bookListItems[position])
        } else {
            (holder as ImageViewHolder).bind(bookListItems[position])
        }

    }



}