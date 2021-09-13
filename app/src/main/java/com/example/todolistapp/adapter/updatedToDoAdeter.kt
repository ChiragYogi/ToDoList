package com.example.todolistapp.adapter

import android.os.Build
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.todolistapp.data.ToDoModal
import com.example.todolistapp.databinding.CompletedTodoRvItemBinding
import com.example.todolistapp.utiles.Utiles

class UpdatedTodoAdepter :
    ListAdapter<ToDoModal, UpdatedTodoAdepter.MyViewHolder>(DiffUtillCallback()) {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {

        val binding = CompletedTodoRvItemBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
        return MyViewHolder(binding)
    }

    @RequiresApi(Build.VERSION_CODES.M)
    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val currentItem = getItem(position)
        holder.bind(currentItem)

    }

    class MyViewHolder(private val binding: CompletedTodoRvItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        @RequiresApi(Build.VERSION_CODES.M)
        fun bind(item: ToDoModal) {
            binding.apply {
                todoTextTitleRv.text = item.title
                timeRvTodo.text = item.time
                dateRvTodo.text = item.date
                Utiles.bacgroundColor(priorityLayout, item.priority)
                priorityTxtLayout.text = item.priority.name

            }
        }
    }

    class DiffUtillCallback : DiffUtil.ItemCallback<ToDoModal>() {
        override fun areItemsTheSame(oldItem: ToDoModal, newItem: ToDoModal): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: ToDoModal, newItem: ToDoModal): Boolean {
            return oldItem == newItem
        }

    }


}

