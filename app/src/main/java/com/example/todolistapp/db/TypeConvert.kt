package com.example.todolistapp.db

import android.widget.RadioButton
import androidx.room.ProvidedTypeConverter
import androidx.room.TypeConverter


@ProvidedTypeConverter
class TypeConvert {

    @TypeConverter
    fun fromRadioButton(radioButton: RadioButton): String{
        return radioButton.toString()
    }
    @TypeConverter
    fun toRadioButton(name: String) :RadioButton{
            return RadioButton(name)
    }
}