package com.okbit.ubook.book

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.view.menu.ActionMenuItemView
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.card.MaterialCardView
import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.Query
import com.okbit.ubook.R



class BookListAdapter(
    query: Query,
    private val listener: BookListAdapterListener
) : FirebaseAdapter<BookListAdapter.SportsViewHolder>(query) {

    class SportsViewHolder(
        itemView: View
    ) : RecyclerView.ViewHolder(itemView) {

        private val cardView: MaterialCardView = itemView.findViewById(R.id.item_book_card)
        private val title: TextView = itemView.findViewById(R.id.item_title_book)
        private val author: TextView = itemView.findViewById(R.id.item_author_book)

        fun bind(snapshot: DocumentSnapshot, listener: BookListAdapterListener) {
            val booksfs: BookModel? = snapshot.toObject(BookModel::class.java)
            title.text = booksfs?.title
            author.text = booksfs?.author

            cardView.setOnClickListener {
                listener.onSportSelected(booksfs)
            }
        }
    }

    interface BookListAdapterListener {
        fun onSportSelected(booksfs: BookModel?)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SportsViewHolder {
        return SportsViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.view_book_item, parent, false
            )
        )
    }

    override fun onBindViewHolder(holder: SportsViewHolder, position: Int) {
        getSnapshot(position)?.let { snapshot ->
            holder.bind(snapshot, listener)
        }
    }
}