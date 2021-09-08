package com.example.todolistapp.adapter


import android.os.Build
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.cardview.widget.CardView
import androidx.core.view.isVisible
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.todolistapp.R
import com.example.todolistapp.data.Priority
import com.example.todolistapp.data.ToDoModal
import com.example.todolistapp.databinding.TodRvItemBinding



class ToDoAdapter(private val listener: OnItemClickListener):
    ListAdapter<ToDoModal,ToDoAdapter.MyViewHolder>( DiffUtillCallback()) {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
         val binding =
             TodRvItemBinding.inflate(LayoutInflater.from(parent.context), parent,false)
        return MyViewHolder(binding)
    }

    @RequiresApi(Build.VERSION_CODES.M)
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

        
        @RequiresApi(Build.VERSION_CODES.M)
        fun bind(item:ToDoModal){
            binding.apply {
                todoTextTitleRv.text = item.title
                dateRvTodo.text = item.date
                timeRvTodo.text = item.time
                reminderImage.isVisible = item.addReminderForToDo
                priorityTxtLayout.text = item.priority.name
                bacgroundColor(priorityLayout,item.priority)
                todoDoneCheckBox.isChecked = item.todoCompleted



            }
        }



    }

    @RequiresApi(Build.VERSION_CODES.M)
    private fun bacgroundColor( cardView: CardView,priority: Priority) {
        when(priority){
            Priority.HIGh ->{cardView.setCardBackgroundColor(cardView.context.getColor(R.color.red))}
            Priority.MEDIUM ->{cardView.setCardBackgroundColor(cardView.context.getColor(R.color.yellow))}
            Priority.LOW ->{cardView.setCardBackgroundColor(cardView.context.getColor(R.color.green))}
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
