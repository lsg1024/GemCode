package com.example.gemcode.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.gemcode.R
import com.example.gemcode.dto.FactoryAndStore

class StoreAdapter : ListAdapter<FactoryAndStore, StoreAdapter.FactoryViewHolder>(StoreDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) : FactoryViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view: View = inflater.inflate(R.layout.factory_name_item, parent,false)
        return FactoryViewHolder(view)
    }

    override fun onBindViewHolder(holder: FactoryViewHolder, position: Int) {
        val user = getItem(position)
        holder.bind(user)
    }

    inner class FactoryViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val factoryNameText : TextView = itemView.findViewById(R.id.item_factory_name)

        fun bind(factoryAndStore: FactoryAndStore) {
            factoryNameText.text = factoryAndStore.name
        }
    }

    class StoreDiffCallback : DiffUtil.ItemCallback<FactoryAndStore>() {
        override fun areItemsTheSame(oldItem: FactoryAndStore, newItem: FactoryAndStore): Boolean {
            return oldItem.name == newItem.name
        }

        override fun areContentsTheSame(oldItem: FactoryAndStore, newItem: FactoryAndStore): Boolean {
            return oldItem == newItem
        }
    }
}