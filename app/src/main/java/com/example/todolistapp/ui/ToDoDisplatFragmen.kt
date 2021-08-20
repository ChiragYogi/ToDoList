package com.example.todolistapp.ui

import android.icu.lang.UCharacter
import android.opengl.Visibility
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import androidx.core.view.isVisible
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.todolistapp.R
import com.example.todolistapp.adapter.ToDoAdapter
import com.example.todolistapp.databinding.FragmentToDoDisplatBinding
import com.example.todolistapp.ui.viemodel.ToDoViewModel


class ToDoDisplatFragmen : Fragment(R.layout.fragment_to_do_displat) {

    private var _binding:FragmentToDoDisplatBinding? = null
    private val binding get() = _binding!!
    lateinit var mAdepter:ToDoAdapter
    private lateinit var viewModel:ToDoViewModel
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        _binding = FragmentToDoDisplatBinding.bind(view)
        mAdepter = ToDoAdapter()
        binding.apply {

            //recyclerView
            todoRv.adapter = mAdepter
            todoRv.layoutManager = LinearLayoutManager(requireContext())

            //using ViewModel

            viewModel = ViewModelProvider(this@ToDoDisplatFragmen).get(ToDoViewModel::class.java)

            viewModel._readAllData.observe(viewLifecycleOwner,{

               if (it.isEmpty()){
                   todoRv.isVisible = false
                   noToDOTxt.isVisible = true
               } else {
                   todoRv.isVisible = true
                   noToDOTxt.isVisible = false
                   mAdepter.setData(it)
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








}