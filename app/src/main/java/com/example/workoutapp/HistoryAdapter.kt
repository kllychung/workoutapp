package com.example.workoutapp

import android.graphics.Color
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.workoutapp.databinding.ItemHistoryBinding

class HistoryAdapter(val list: ArrayList<HistoryEntity>):RecyclerView.Adapter<HistoryAdapter.ViewHolder>() {

    class ViewHolder(binding: ItemHistoryBinding): RecyclerView.ViewHolder(binding?.root){
        val date = binding.tvItem
        val position = binding.tvPosition
        val ll_history_item_main = binding.llHistoryItemMain
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(ItemHistoryBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.position.text = (position + 1).toString()
        holder.date.text = list[position].date

        if (position % 2 == 0){
            holder.ll_history_item_main.setBackgroundColor(Color.LTGRAY)
        }
        else {
            holder.ll_history_item_main.setBackgroundColor(Color.WHITE)
        }
    }

    override fun getItemCount(): Int {
        Log.e("list size ",list.size.toString())
        return list.size
    }
}