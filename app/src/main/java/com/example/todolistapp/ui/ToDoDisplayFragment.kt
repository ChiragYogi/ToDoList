package com.example.todolistapp.ui

import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.todolistapp.R
import com.example.todolistapp.adapter.ToDoAdapter
import com.example.todolistapp.data.ToDoModal
import com.example.todolistapp.databinding.FragmentDisplayTodoBinding
import com.example.todolistapp.ui.viemodel.ToDoViewModel
import com.example.todolistapp.utiles.observeOnce
import com.google.android.material.snackbar.Snackbar


class ToDoDisplayFragment : Fragment(R.layout.fragment_display_todo),
    ToDoAdapter.OnItemClickListener {

    private  val viewModel:ToDoViewModel by viewModels()

    private var _binding:FragmentDisplayTodoBinding? = null
    private val binding get() = _binding!!

    private val mAdepter:ToDoAdapter by lazy { ToDoAdapter(this) }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        _binding = FragmentDisplayTodoBinding.bind(view)

        binding.apply {

            //recyclerView
             todoRv.apply {
                adapter = mAdepter
                todoRv.layoutManager = LinearLayoutManager(requireContext())
                setHasFixedSize(true)
                 swipeToDelete(todoRv)
            }
            //
            viewModel.readAllToDo.observe(viewLifecycleOwner){ todoList ->

               if (todoList.isNotEmpty()){
                   todoRv.isVisible = true
                   noToDOTxt.isVisible = false
                   mAdepter.submitList(todoList)
                   Log.d("ToDoLog","we got resultFrom Database $todoList")

               } else {
                   todoRv.isVisible = false
                   noToDOTxt.isVisible = true

               }
            }


            newTodo.setOnClickListener {
                findNavController().navigate(R.id.action_toDoDisplayFragment_to_itemToDoFragment2)
            }


        }

        setHasOptionsMenu(true)
    }

    override fun onItemClick(toDoModal: ToDoModal) {
        val action = ToDoDisplayFragmentDirections
            .actionToDoDisplayFragmentToUpdateFragment(currentTodo = toDoModal)
        findNavController().navigate(action)
    }

    override fun onCheckBoxClickListener(toDoModal: ToDoModal, isChecked: Boolean) {
        viewModel.onToDoChecked(toDoModal,isChecked)
    }

    private fun swipeToDelete(recyclerView: RecyclerView) {

        val swipeToDeleteCallBack = object : ItemTouchHelper.SimpleCallback(
            0,
            ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT
        ) {
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
                restoreDefault(viewHolder.itemView, deleteToDo)
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
    private fun searchDataBase(searchTxt: String){

        viewModel.searchDatabase(searchTxt).observeOnce(viewLifecycleOwner,{ searchList ->
            mAdepter.submitList(searchList)
        })
    }



    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
       inflater.inflate(R.menu.action_bar_menu,menu)

        val searchItem = menu.findItem(R.id.action_search)
        val searchView = searchItem.actionView as androidx.appcompat.widget.SearchView

        searchView.isSubmitButtonEnabled = true
        searchView.setOnQueryTextListener(object:  androidx.appcompat.widget.SearchView.OnQueryTextListener{


            override fun onQueryTextSubmit(query: String?): Boolean {
               if (query != null){
                   searchDataBase(query)
                   searchView.clearFocus()

               }
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                if (newText != null){
                    searchDataBase(newText)
                }
                return true
            }

        })
    }




    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        when(item.itemId){
           R.id.delete_all -> findNavController().navigate(R.id.action_global_deleteDialog2)
            R.id.sort_by_high_priority -> {
                viewModel.sortByHighPriority.observe(viewLifecycleOwner,{
                        mAdepter.submitList(it)
            })
            }
            R.id.sort_by_medium_priority -> {
                viewModel.sortByMediumPriority.observe(viewLifecycleOwner,{
                    mAdepter.submitList(it)
                })
            }

            R.id.sort_by_low_priority -> {
            viewModel.sortByLowPriority.observe(viewLifecycleOwner,{
                mAdepter.submitList(it)
            })}
        }



        return super.onOptionsItemSelected(item)

    }



    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null


    }
}