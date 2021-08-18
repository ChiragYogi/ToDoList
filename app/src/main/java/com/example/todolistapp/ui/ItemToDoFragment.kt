package com.example.todolistapp.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import com.example.todolistapp.R
import com.example.todolistapp.databinding.FragmentItemToDoBinding


class ItemToDoFragment : Fragment(R.layout.fragment_item_to_do) {

    private var _binding:FragmentItemToDoBinding? = null
    private val binding get() = _binding!!

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        _binding = FragmentItemToDoBinding.bind(view)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}