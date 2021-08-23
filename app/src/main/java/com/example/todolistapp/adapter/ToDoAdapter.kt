package com.example.todolistapp.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.todolistapp.data.ToDoModal

import com.example.todolistapp.databinding.TodRvItemBinding
import com.example.todolistapp.ui.ToDoDisplatFragmen


class ToDoAdapter(private val listener: OnItemClickListener):
    ListAdapter<ToDoModal,ToDoAdapter.MyViewHolder>( DiffUtillCallback()) {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
         val binding =
             TodRvItemBinding.inflate(LayoutInflater.from(parent.context), parent,false)
        return MyViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val currentItem = getItem(position)
        holder.bind(currentItem)
    }




    inner class MyViewHolder(private val binding:TodRvItemBinding):RecyclerView.ViewHolder(binding.root) {

        init {
            binding.apply {

                //click listener for updating todo
                root.setOnClickListener {
                    val position = adapterPosition
                    if (position != RecyclerView.NO_POSITION){
                        val todo = getItem(position)
                        listener.onItemClick(todo)
                    }
                }

                //click listener for completed todo
                todoDoneCheckBox.setOnClickListener{
                    val position = adapterPosition
                    if (position != RecyclerView.NO_POSITION){
                        val todoCheck = getItem(position)
                        listener.onCheckBoxClickListener(todoCheck,todoDoneCheckBox.isChecked)
                    }
                }

            }
        }

        fun bind(item:ToDoModal){
            binding.apply {
                todoTextTitleRv.text = item.title
                dateRvTodo.text = item.date
                timeRvTodo.text = item.time
                priorityRvTv.text = item.priority
            }
        }



    }

    class DiffUtillCallback: DiffUtil.ItemCallback<ToDoModal>() {
        override fun areItemsTheSame(oldItem: ToDoModal, newItem: ToDoModal): Boolean {
           return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: ToDoModal, newItem: ToDoModal): Boolean {
         return oldItem == newItem
        }
    }

    interface OnItemClickListener {
        fun onItemClick(toDoModal: ToDoModal)
        fun onCheckBoxClickListener(toDoModal: ToDoModal, isChecked: Boolean)
    }
}