package com.example.todolistapp.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import com.example.todolistapp.R
import com.example.todolistapp.databinding.FragmentToDoDisplatBinding


class ToDoDisplatFragmen : Fragment(R.layout.fragment_to_do_displat) {

    private var _binding:FragmentToDoDisplatBinding? = null
    private val binding get() = _binding!!
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        _binding = FragmentToDoDisplatBinding.bind(view)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }








}