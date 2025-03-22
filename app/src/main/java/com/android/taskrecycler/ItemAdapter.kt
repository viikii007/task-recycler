package com.android.taskrecycler

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class ItemAdapter : RecyclerView.Adapter<ItemAdapter.ItemViewHolder>() {
    private val items = mutableListOf<String>()

     var itemClickListener: ((String) -> Unit)? = null

    fun addItems(newItems: List<String>) {
        val startPosition = items.size
        items.addAll(newItems)
        notifyItemRangeInserted(startPosition, newItems.size)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_view, parent, false)
        return ItemViewHolder(view)
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        val item = items[position]
        holder.bind(item)
        holder.itemView.setOnClickListener {
            itemClickListener?.invoke(item)
        }
    }

    override fun getItemCount(): Int = items.size

    class ItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val textView: TextView = itemView.findViewById(R.id.textView)
        fun bind(text: String) {
            textView.text = text
        }
    }
}