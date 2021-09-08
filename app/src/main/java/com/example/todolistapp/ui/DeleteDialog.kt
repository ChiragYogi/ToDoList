package com.example.todolistapp.ui

import android.app.AlertDialog
import android.app.Dialog
import android.os.Bundle
import android.widget.Toast
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.viewModels
import com.example.todolistapp.R
import com.example.todolistapp.ui.viemodel.ToDoViewModel
import com.google.android.material.dialog.MaterialAlertDialogBuilder

class DeleteDialog:DialogFragment() {

        private val viewModel: ToDoViewModel by viewModels()
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog =
        MaterialAlertDialogBuilder(requireContext())
            .setTitle(getString(R.string.dialog_title))
            .setMessage(getString(R.string.dialog_message))
             .setIcon(R.drawable.ic_baseline_delete_forever_24)
            .setPositiveButton(getString(R.string.dialog_yes_button)){ _, _  ->
               viewModel.deleteCompletedTodo()
                Toast.makeText(
                    requireContext(),
                    "All Completed ToDo Deleted SuccessFully",
                    Toast.LENGTH_SHORT).show()
            }
            .setNegativeButton(getString(R.string.dialog_no_button)){ dialog, _ ->
                dialog.dismiss()
                Toast.makeText(
                    requireContext(),
                    "Dialog Dismissed",
                    Toast.LENGTH_SHORT).show()

            }.create()

}