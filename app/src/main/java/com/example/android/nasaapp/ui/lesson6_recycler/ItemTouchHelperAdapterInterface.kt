package com.example.android.nasaapp.ui.lesson6_recycler

interface ItemTouchHelperAdapterInterface {
    fun onItemMove(fromPosition: Int, toPosition: Int)
    fun onItemDismiss(position: Int)
}