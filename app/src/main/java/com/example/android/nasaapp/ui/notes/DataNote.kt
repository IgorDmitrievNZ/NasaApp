package com.example.android.nasaapp.ui.notes

const val TYPE_NOTE = 0

data class DataNote(
    val noteTitle: String? = "Title",
    val noteDescription: String? = "Description",
    val type: Int = TYPE_NOTE
)
