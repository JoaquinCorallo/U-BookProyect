package com.okbit.ubook.firestore


import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.google.android.material.card.MaterialCardView
import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.Query
import com.okbit.ubook.R
import java.util.*

class BooksAdapter(
    query: Query,
    private val listener: BooksAdapterListener
) : FirestoreAdapter<BooksAdapter.BooksViewHolder>(query) {

    class BooksViewHolder(
        itemView: View
    ) : RecyclerView.ViewHolder(itemView) {

        private val cardView: MaterialCardView = itemView.findViewById(R.id.item_firestorebook_card)
        private val title: TextView = itemView.findViewById(R.id.item_firestorebook_title)
        private val author: TextView = itemView.findViewById(R.id.item_firestorebook_author)
        private val condition: TextView = itemView.findViewById(R.id.item_firestorebook_condition)
        private val portadainicio: ImageView = itemView.findViewById(R.id.item_firestorebook_cover)


        fun bind(snapshot: DocumentSnapshot, listener: BooksAdapterListener) {
            val books: Books? = snapshot.toObject(Books::class.java)
            title.text = books?.title
            author.text = books?.author
            condition.text = books?.condition
            val imageByteArray = Base64.getDecoder().decode(books?.cover)
            if (books?.cover != "" ){
                Glide.with(itemView.context).asBitmap().load(imageByteArray).into(portadainicio)
            }else{
                Glide.with(itemView.context).load(books?.coverurl).into(portadainicio)
            }


            cardView.setOnClickListener {
                listener.onBookSelected(books)
            }
        }
    }

    interface BooksAdapterListener {
        fun onBookSelected(books: Books?)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BooksViewHolder {
        return BooksViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.item_book, parent, false
            )
        )
    }

    override fun onBindViewHolder(holder: BooksViewHolder, position: Int) {
        getSnapshot(position)?.let { snapshot ->
            holder.bind(snapshot, listener)
        }
    }
}