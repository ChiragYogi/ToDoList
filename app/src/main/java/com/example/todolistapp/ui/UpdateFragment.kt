package com.example.todolistapp.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.todolistapp.R
import com.example.todolistapp.alaram.SetAlaram
import com.example.todolistapp.data.Priority
import com.example.todolistapp.data.ToDoModal
import com.example.todolistapp.databinding.FragmentUpdateTodoBinding
import com.example.todolistapp.ui.viemodel.ToDoViewModel
import com.example.todolistapp.utiles.transformDatePicker
import com.example.todolistapp.utiles.transformTimePicker
import com.example.todolistapp.utiles.utills

import com.example.todolistapp.utiles.utills.priority
import com.example.todolistapp.utiles.utills.selectPriority
import kotlinx.android.synthetic.main.add_todo_layout.*
import java.util.*


class UpdateFragment : Fragment(R.layout.fragment_update_todo) {

    private var _binding: FragmentUpdateTodoBinding? = null
    private val binding get() = _binding!!
    private val viewModel: ToDoViewModel by viewModels()
    /*private val args by navArgs<UpdateFragmentArgs>()*/
    private val args by navArgs<UpdateFragmentArgs>()


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentUpdateTodoBinding.bind(view)

        val toDoModal = args.currentTodo
        if (toDoModal != null) {
            loadData(toDoModal)
        }
        initView()

        binding.updateToDo.setOnClickListener {
            saveUpdatedTodo()
        }

    }



    private fun loadData(toDoModal: ToDoModal) = with(binding) {

        updateToDoLayout.apply {

            edtInputLayout.setText(toDoModal.title)
            dateEdt.setText(toDoModal.date)
            timeEdt.setText(toDoModal.time)
            etPrioritySpinner.setText(toDoModal.priority.name)
            reminderSwitch.isChecked = toDoModal.addReminderForToDo
        }
    }

private fun initView() {

    val priorityAdepter = ArrayAdapter(
        requireContext(),
        R.layout.item_auto_complete_dropdown, priority
    )

    binding.updateToDoLayout.apply {

        etPrioritySpinner.setAdapter(priorityAdepter)

        dateEdt.transformDatePicker(requireContext(), "dd/MM/yyyy", Date())
        timeEdt.transformTimePicker(requireContext(), "h:mm a")

    }
}

    private fun saveUpdatedTodo() {
        binding.updateToDoLayout.apply {

            val (title, date, time, _, reminder) = updateTodo()
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
                    viewModel.updateTodo(updateTodo())

                        if (reminder){
                            val alarmClass = SetAlaram(requireContext())
                            alarmClass.setAlaramForRemiderToDO(updateTodo())
                        }

                    Toast.makeText(
                        requireContext(), "ToDo Updated Successfully", Toast.LENGTH_LONG
                    ).show()

                    //navigation back to previous Fragment
                    findNavController().navigateUp()

                }
            }
        }
    }

    private fun updateTodo(): ToDoModal  {

        binding.updateToDoLayout.let {


            val titleOfTodo = it.edtInputLayout.text.toString()
            val date = it.dateEdt.text.toString()
            val time = it.timeEdt.text.toString()
            val reminder = it.reminderSwitch.isChecked
            val priorityTxt = it.etPrioritySpinner.text.toString()


            val priority: Priority = if (priorityTxt == args.currentTodo?.priority?.name){
                args.currentTodo!!.priority

            }else{
                selectPriority(priorityTxt)
            }
            val id = args.currentTodo!!.id



            return ToDoModal(
                title = titleOfTodo,
                date = date,
                time = time,
                priority = priority,
                addReminderForToDo = reminder,
                id = id
            )
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }




}