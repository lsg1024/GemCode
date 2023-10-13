package com.example.gemcode.adapter

import android.graphics.Color
import android.graphics.Typeface
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView.OnItemClickListener
import android.widget.Filter
import android.widget.Filterable
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.gemcode.R
import com.example.gemcode.dto.FactoryAndStore
import java.util.Objects

class StoreAdapter(private val listener: OnStoreSelectedListener) : ListAdapter<FactoryAndStore, StoreAdapter.FactoryViewHolder>(StoreDiffCallback()), Filterable {

    private var originalList : List<FactoryAndStore> = emptyList()
    private var selectedPosition = -1

    // 아이템 클릭 이벤트 리스너
    private val onItemClickListener: (position: Int) -> Unit = { position ->
        if (selectedPosition != position) {
            val previousSelectedPosition = selectedPosition
            selectedPosition = position
            notifyItemChanged(previousSelectedPosition)
            notifyItemChanged(selectedPosition)

            listener.onStoreSelected(getItem(position))
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) : FactoryViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view: View = inflater.inflate(R.layout.factory_name_item, parent,false)
        return FactoryViewHolder(view)
    }

    override fun onBindViewHolder(holder: FactoryViewHolder, position: Int) {
        val store = getItem(position)
        holder.bind(store, position, selectedPosition, onItemClickListener)
    }

    inner class FactoryViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val factoryNameText : TextView = itemView.findViewById(R.id.item_factory_name)

        fun bind(factoryAndStore: FactoryAndStore, position: Int, selectedPosition : Int, onItemClickListener: (position: Int) -> Unit) {
            factoryNameText.text = factoryAndStore.name

            // 아이템 클릭 시 호출될 리스너
            itemView.setOnClickListener {
                onItemClickListener(adapterPosition)
            }

            // 선택된 아이템의 스타일 변경
            if (position == selectedPosition) {
                factoryNameText.setBackgroundColor(Color.LTGRAY)
                factoryNameText.setTypeface(null, Typeface.BOLD)
            } else {
                // 선택되지 않은 아이템의 기본 스타일로 변경
                factoryNameText.setBackgroundColor(Color.WHITE)
                factoryNameText.setTypeface(null, Typeface.NORMAL)
            }

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

    override fun getFilter(): Filter {
        return object : Filter() {
            override fun performFiltering(p0: CharSequence?): FilterResults {
                val filteredList = mutableListOf<FactoryAndStore>()
                if (p0.isNullOrBlank()) {
                    filteredList.addAll(originalList)
                } else {
                    val filterPattern = p0.toString().trim()

                    for (item in originalList) {
                        if (item.name.contains(filterPattern, ignoreCase = true)) {
                            filteredList.add(item)
                        }
                    }
                }
                val filterResult = FilterResults()
                filterResult.values = filteredList
                return filterResult
            }

            override fun publishResults(p0: CharSequence?, p1: FilterResults?) {
                submitList(p1?.values as List<FactoryAndStore>)
            }
        }
    }
    fun submitOriginalList(list: List<FactoryAndStore>) {
        originalList = list
        submitList(list)
    }

    interface OnStoreSelectedListener {
        fun onStoreSelected(factoryAndStore: FactoryAndStore)
    }

}