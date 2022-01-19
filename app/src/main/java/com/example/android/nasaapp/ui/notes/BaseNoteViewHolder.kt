package com.example.android.nasaapp.ui.notes

import android.view.View
import androidx.recyclerview.widget.RecyclerView

abstract class BaseNoteViewHolder(view: View) : RecyclerView.ViewHolder(view) {

    abstract fun bind(data: DataNote)
}