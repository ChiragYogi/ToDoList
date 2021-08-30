package com.example.todolistapp.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.todolistapp.R
import com.example.todolistapp.adapter.ToDoAdapter
import com.example.todolistapp.data.ToDoModal
import com.example.todolistapp.databinding.FragmentToDoDisplatBinding
import com.example.todolistapp.ui.viemodel.ToDoViewModel
import com.google.android.material.snackbar.Snackbar


class ToDoDisplatFragmen : Fragment(R.layout.fragment_to_do_displat),
    ToDoAdapter.OnItemClickListener {

    private  val viewModel:ToDoViewModel by viewModels()

    private var _binding:FragmentToDoDisplatBinding? = null
    private val binding get() = _binding!!

    private val mAdepter:ToDoAdapter by lazy { ToDoAdapter(this) }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        _binding = FragmentToDoDisplatBinding.bind(view)

        binding.apply {


            //recyclerView
             todoRv.apply {
                todoRv.adapter = mAdepter
                todoRv.layoutManager = LinearLayoutManager(requireContext())
                setHasFixedSize(true)
                 swipeToDelete(todoRv)
            }
            




               viewModel.readAllToDo.observe(viewLifecycleOwner,{

               if (it.isEmpty()){
                   todoRv.isVisible = false
                   noToDOTxt.isVisible = true
               } else {
                   todoRv.isVisible = true
                   noToDOTxt.isVisible = false
                   mAdepter.submitList(it)
               }
            })


            newTodo.setOnClickListener {
                findNavController().navigate(R.id.action_toDoDisplatFragmen_to_itemToDoFragment2)
            }


        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null

    }

    override fun onItemClick(toDoModal: ToDoModal) {

    }

    override fun onCheckBoxClickListener(toDoModal: ToDoModal, isChecked: Boolean) {
        viewModel.onToDoChecked(toDoModal,isChecked)
    }

    private fun swipeToDelete(recyclerView: RecyclerView){

        val swipeToDeleteCallBack = object : ItemTouchHelper.SimpleCallback(0,
            ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT
        ){
            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ): Boolean {
                return false
            }
            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {

                val deleteToDo = mAdepter.currentList[viewHolder.adapterPosition]
                viewModel.deleteTodo(deleteToDo)
                restoreDefault(viewHolder.itemView,deleteToDo)
            }
        }

        val itemTouchHelper = ItemTouchHelper(swipeToDeleteCallBack)
        itemTouchHelper.attachToRecyclerView(recyclerView)

    }

    private fun restoreDefault(view: View, deleteItem: ToDoModal){
            val snakeBar =
                Snackbar.make(view,"ToDo is Deleted Successfully",Snackbar.LENGTH_LONG)
                snakeBar .setAction("UNDO"){
                    viewModel.addTodo(deleteItem)
                }
                snakeBar.show()
    }



}