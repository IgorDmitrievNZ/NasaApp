package com.example.android.nasaapp.ui.lesson6_recycler

import android.view.View
import androidx.recyclerview.widget.RecyclerView

abstract class BaseViewHolder(view: View) : RecyclerView.ViewHolder(view) {
//    abstract fun bind(data: Data) //old
abstract fun bind(data: Pair<Data,Boolean>)  //new
}