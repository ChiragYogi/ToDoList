package com.example.todolistapp.data

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
enum class Priority: Parcelable {
    High,
    Medium,
    Low
}