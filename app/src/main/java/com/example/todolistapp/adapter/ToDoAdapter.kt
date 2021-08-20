package com.example.todolistapp.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.todolistapp.data.ToDoModal

import com.example.todolistapp.databinding.TodRvItemBinding


class ToDoAdapter:RecyclerView.Adapter<ToDoAdapter.MyViewHolder>() {

    private var data = emptyList<ToDoModal>()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
         val binding =
             TodRvItemBinding.inflate(LayoutInflater.from(parent.context), parent,false)
        return MyViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val currentItem = data[position]
        holder.bind(currentItem)
    }

    override fun getItemCount(): Int {
        return data.size
    }

    fun setData(todo: List<ToDoModal>){
        this.data = todo
        notifyDataSetChanged()
    }

    class MyViewHolder(private val binding:TodRvItemBinding):RecyclerView.ViewHolder(binding.root) {

        fun bind(item:ToDoModal){
            binding.apply {
                todoTextTitleRv.text = item.title
                dateRvTodo.text = item.date.toString()
                timeRvTodo.text = item.time.toString()
                priorityRvTv.text = item.priority.toString()
            }
        }

    }
}