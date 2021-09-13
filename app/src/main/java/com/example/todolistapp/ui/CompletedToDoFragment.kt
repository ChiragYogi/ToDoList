package com.example.todolistapp.ui

import android.graphics.*
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import androidx.fragment.app.Fragment

import android.view.View

import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.todolistapp.R
import com.example.todolistapp.adapter.UpdatedTodoAdepter
import com.example.todolistapp.databinding.FragmentCompltedToDoBinding
import com.example.todolistapp.ui.viemodel.ToDoViewModel
import kotlinx.android.synthetic.main.fragment_complted_to_do.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope


class CompletedToDoFragment : Fragment(R.layout.fragment_complted_to_do) {

    private var _binding: FragmentCompltedToDoBinding? = null
    private val binding get() = _binding!!
    private val vieModel: ToDoViewModel by viewModels()

    private val mAdepter: UpdatedTodoAdepter by lazy { UpdatedTodoAdepter() }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentCompltedToDoBinding.bind(view)

        binding.apply {
            updateLayoutRv.apply {
                adapter = mAdepter
                layoutManager = LinearLayoutManager(requireContext())
                setHasFixedSize(true)
            }
        }

        vieModel.updatedToDo.observe(viewLifecycleOwner,{ todoList ->
            mAdepter.submitList(todoList)
        })

        deletedToDoSwipe()
        setHasOptionsMenu(true)

    }




    //deleting updated todo on swipe
    private fun deletedToDoSwipe() {
        val simpleItemTouchCallback = object : ItemTouchHelper.SimpleCallback(
            0,
            ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT
        ) {
            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ): Boolean = false

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
               if (direction == ItemTouchHelper.LEFT) {
                    val deletedTodo = mAdepter.currentList[viewHolder.adapterPosition]
                    vieModel.deleteTodo(deletedTodo)
               }
                 else if (direction == ItemTouchHelper.RIGHT) {
                     val deletedTodo = mAdepter.currentList[viewHolder.adapterPosition]
                    vieModel.deleteTodo(deletedTodo)
                }
            }

            //draw color on surface with delete png
            override fun onChildDraw(
                canvas: Canvas,
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                dX: Float,
                dY: Float,
                actionState: Int,
                isCurrentlyActive: Boolean
            ) {
                if (actionState == ItemTouchHelper.ACTION_STATE_SWIPE) {
                    val itemView = viewHolder.itemView

                    val paint = Paint()
                    val icon: Bitmap

                    if (dX > 0) {

                        icon = BitmapFactory.decodeResource(resources, R.mipmap.ic_delete)

                        paint.color = Color.parseColor("#D32F2F")

                        canvas.drawRect(
                            itemView.left.toFloat(), itemView.top.toFloat(),
                            itemView.left.toFloat() + dX, itemView.bottom.toFloat(), paint
                        )

                        canvas.drawBitmap(
                            icon,
                            itemView.left.toFloat(),
                            itemView.top.toFloat() + (itemView.bottom.toFloat() - itemView.top.toFloat() - icon.height.toFloat()) / 2,
                            paint
                        )


                    } else {
                        icon = BitmapFactory.decodeResource(resources, R.mipmap.ic_delete)

                        paint.color = Color.parseColor("#D32F2F")

                        canvas.drawRect(
                            itemView.right.toFloat() + dX, itemView.top.toFloat(),
                            itemView.right.toFloat(), itemView.bottom.toFloat(), paint
                        )

                        canvas.drawBitmap(
                            icon,
                            itemView.right.toFloat() - icon.width,
                            itemView.top.toFloat() + (itemView.bottom.toFloat() - itemView.top.toFloat() - icon.height.toFloat()) / 2,
                            paint
                        )
                    }
                    viewHolder.itemView.translationX = dX


                } else {
                    super.onChildDraw(
                        canvas,
                        recyclerView,
                        viewHolder,
                        dX,
                        dY,
                        actionState,
                        isCurrentlyActive
                    )
                }
            }


        }

        val itemTouchHelper = ItemTouchHelper(simpleItemTouchCallback)
        itemTouchHelper.attachToRecyclerView(updateLayoutRv)
    }

    // inflating menu
    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.complet_todo_menu,menu)
    }


    // Alert Dialog for deleting all todo
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.delete_all -> findNavController().navigate(R.id.action_global_deleteDialog2)
        }

        return super.onOptionsItemSelected(item)
    }
}