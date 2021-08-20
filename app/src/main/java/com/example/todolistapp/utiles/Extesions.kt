package com.example.todolistapp.utiles

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.content.Context
import android.widget.DatePicker
import com.google.android.material.textfield.TextInputEditText
import java.text.SimpleDateFormat
import java.util.*


fun TextInputEditText.transformDatePicker(
    context: Context,
    format: String,
    maxDate: Date? = null
){

    isFocusableInTouchMode = false
    isClickable = true
    isFocusable = false

    val myCalender = Calendar.getInstance()

    val datePickerSetListener =
        DatePickerDialog.OnDateSetListener { _, year, month, dayOfMonth ->
            myCalender.set(Calendar.YEAR,year)
            myCalender.set(Calendar.MONTH,month)
            myCalender.set(Calendar.DAY_OF_MONTH,dayOfMonth)
            val sdf = SimpleDateFormat(format, Locale.UK)
            setText(sdf.format(myCalender.time))

        }

    setOnClickListener {
        DatePickerDialog(
            context,datePickerSetListener,myCalender.get(Calendar.YEAR),myCalender.get(Calendar.MONTH),
            myCalender.get(Calendar.DAY_OF_MONTH)
        ).run {
            maxDate?.time?.also { datePicker.minDate = it }
            show()
        }
    }
}

fun TextInputEditText.transformTimePicker(
    context: Context,
    format: String
    ) {

    isFocusableInTouchMode = false
    isClickable = true
    isFocusable = false

    val myCalender = Calendar.getInstance()

    val timePickListener = TimePickerDialog.OnTimeSetListener { _, hourOfDay, minute ->

        myCalender.set(Calendar.HOUR_OF_DAY, hourOfDay)
        myCalender.set(Calendar.MINUTE, minute)

        val selectTime = SimpleDateFormat(format, Locale.ENGLISH)
        setText(selectTime.format(myCalender.time))

    }

   setOnClickListener {
       TimePickerDialog(
           context, timePickListener,
           myCalender.get(Calendar.HOUR_OF_DAY), myCalender.get(Calendar.MINUTE), false
       ).show()
   }
}