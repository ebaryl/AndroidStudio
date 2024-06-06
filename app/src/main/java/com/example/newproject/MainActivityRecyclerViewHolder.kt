package com.example.newproject

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.newproject.databinding.RecyclerViewBinding

class MainActivityRecyclerViewHolder(val binding: RecyclerViewBinding): RecyclerView.ViewHolder(binding.root)

class MainActivityAdapter(
    val data: List<RecyclerItemData>,
    val onItemClick: (RecyclerItemData) -> Unit
) : RecyclerView.Adapter<MainActivityRecyclerViewHolder>() {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): MainActivityRecyclerViewHolder {
        val binding = RecyclerViewBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MainActivityRecyclerViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MainActivityRecyclerViewHolder, position: Int) {
        val currentData = data[position] // Access the item from the list using the position
        holder.binding.mainActivityRecyclerTextView.text = currentData.name
        holder.binding.mainActivityRecyclerButton.setOnClickListener { onItemClick.invoke(currentData) }
    }

    override fun getItemCount() = data.size
}
