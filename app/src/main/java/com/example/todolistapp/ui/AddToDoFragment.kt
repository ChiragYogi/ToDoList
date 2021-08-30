package com.example.todolistapp.ui


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.todolistapp.R
import com.example.todolistapp.data.ToDoModal
import com.example.todolistapp.databinding.FragmentItemToDoBinding
import com.example.todolistapp.ui.viemodel.ToDoViewModel
import com.example.todolistapp.utiles.Constant
import com.example.todolistapp.utiles.transformDatePicker
import com.example.todolistapp.utiles.transformTimePicker
import java.util.*


class AddToDoFragment :Fragment(R.layout.fragment_item_to_do) {

    private var _binding: FragmentItemToDoBinding? = null
    private val binding get() = _binding!!
    private lateinit var viewModel: ToDoViewModel

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        _binding = FragmentItemToDoBinding.bind(view)

        viewModel = ViewModelProvider(this).get(ToDoViewModel::class.java)

       initView()

    }

    private fun initView(){

        val priorityAdepter = ArrayAdapter(
            requireContext(),
            R.layout.item_auto_complete_dropdown,
            Constant.priority)

        binding.addToDoLayout.apply {

          etPrioritySpinner.setAdapter(priorityAdepter)
            dateEdt.transformDatePicker(requireContext(),"dd/MM/yyyy",Date())
            timeEdt.transformTimePicker(requireContext(), "h:mm a" )


        }

        binding.saveToDoFab.setOnClickListener {
            insertToDoIntoDatabase()
        }
    }

    private fun insertToDoIntoDatabase() {

        val (_, title, date, time) = addToDo()


        with(binding.addToDoLayout) {
            when {
                title.isEmpty() -> {
                    this.edtInputLayout.error = getString(R.string.error_msg_for_title_tinl)
                }
                date.isEmpty() -> {
                    this.dateEdt.error = getString(R.string.date_error)
                }
                time.isEmpty() -> {
                    this.timeEdt.error = getString(R.string.time_error)
                }
                else -> {
                    viewModel.addTodo(addToDo())
                    Toast.makeText(
                        requireContext(), "ToDo Added Successfully",Toast.LENGTH_LONG).show()
                    //navigation back to previous Fragment
                    findNavController().navigateUp()

                }
            }
        }


    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun addToDo(): ToDoModal = binding.addToDoLayout.let {
        val titleOfTodo = it.edtInputLayout.text.toString()
        val date = it.dateEdt.text.toString()
        val time = it.timeEdt.text.toString()
        val reminder = it.reminderSwitch.isChecked
        val priority = it.etPrioritySpinner.text.toString()

        return ToDoModal(0, titleOfTodo, date, time,priority,reminder)
    }






}





