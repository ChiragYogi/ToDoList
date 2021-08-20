package com.example.todolistapp.ui


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import android.widget.RadioButton
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.todolistapp.R
import com.example.todolistapp.data.ToDoModal
import com.example.todolistapp.databinding.FragmentItemToDoBinding
import com.example.todolistapp.ui.viemodel.ToDoViewModel
import com.example.todolistapp.utiles.transformDatePicker
import com.example.todolistapp.utiles.transformTimePicker
import java.util.*


class ItemToDoFragment : Fragment(R.layout.fragment_item_to_do) {

    private var _binding: FragmentItemToDoBinding? = null
    private val binding get() = _binding!!
    private lateinit var viewModel: ToDoViewModel


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        _binding = FragmentItemToDoBinding.bind(view)

        viewModel = ViewModelProvider(this).get(ToDoViewModel::class.java)

        binding.apply {

            saveToDoFab.setOnClickListener {
                insertToDoIntoDatabase()
            }

            dateEdt.transformDatePicker(
                requireContext(),
                "dd/MM/yyyy",
                Date()
            )

            timeEdt.transformTimePicker(
                requireContext(), "h:mm a"
            )

        }


    }

    private fun insertToDoIntoDatabase() {

        val (_, title, date, time, radioButton) = addingtodo()
        with(binding) {
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
                    viewModel.addTodo(addingtodo())
                    Toast.makeText(
                        requireContext(), "ToDo Added Successfully",
                        Toast.LENGTH_LONG
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

    private fun addingtodo(): ToDoModal = binding.let {
        val titleOfTodo = it.edtInputLayout.text.toString()
        val reminder = it.todoCheck.isChecked
        val checkButtonId = it.todoRadioGroup.checkedRadioButtonId
        val radioButton =
            it.todoRadioGroup.findViewById<RadioButton>(checkButtonId)?.text.toString()

        val date = it.dateEdt.text.toString()
        val time = it.timeEdt.text.toString()

        return ToDoModal(0, titleOfTodo, date, time, reminder, radioButton)
    }


}





