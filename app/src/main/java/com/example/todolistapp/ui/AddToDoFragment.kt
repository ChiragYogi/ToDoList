package com.example.todolistapp.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.todolistapp.R
import com.example.todolistapp.alaram.SetAlaram
import com.example.todolistapp.data.ToDoModal
import com.example.todolistapp.databinding.FragmentAddTodoBinding
import com.example.todolistapp.ui.viemodel.ToDoViewModel
import com.example.todolistapp.utiles.transformDatePicker
import com.example.todolistapp.utiles.transformTimePicker
import com.example.todolistapp.utiles.utills.priority
import com.example.todolistapp.utiles.utills.selectPriority

import java.util.*


class AddToDoFragment : Fragment(R.layout.fragment_add_todo) {

    private var _binding: FragmentAddTodoBinding? = null
    private val binding get() = _binding!!
    private val viewModel: ToDoViewModel by viewModels()
    private lateinit var alarmClass: SetAlaram



    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        _binding = FragmentAddTodoBinding.bind(view)

          initView()

    }

    private fun initView() {

        val priorityAdepter = ArrayAdapter(
            requireContext(),
            R.layout.item_auto_complete_dropdown,priority)

        binding.addToDoLayout.apply {

            etPrioritySpinner.setAdapter(priorityAdepter)

            dateEdt.transformDatePicker(requireContext(), "dd/MM/yyyy", Date())
            timeEdt.transformTimePicker(requireContext(), "h:mm a")
        }

        binding.saveToDoFab.setOnClickListener {
            insertToDoIntoDatabase()
        }
    }

    private fun insertToDoIntoDatabase() {

        val (title, date, time, _, reminder) = addToDo()


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
                    if (reminder){
                        val alarmClass = SetAlaram(requireContext())
                        alarmClass.setAlaramForRemiderToDO(addToDo())
                    }
                     Toast.makeText(
                        requireContext(), "ToDo Added Successfully", Toast.LENGTH_LONG
                    ).show()



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
        val priorityTxt = it.etPrioritySpinner.text.toString()
        val priority = selectPriority(priorityTxt)




        return ToDoModal( titleOfTodo, date, time, priority, reminder)


    }




}





