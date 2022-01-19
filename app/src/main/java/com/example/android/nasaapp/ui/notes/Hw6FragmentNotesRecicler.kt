package com.example.android.nasaapp.ui.notes

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.ItemTouchHelper
import com.example.android.nasaapp.BaseFragment
import com.example.android.nasaapp.databinding.FragmentHw6NotesRecyclerBinding
import com.example.android.nasaapp.ui.lesson6_recycler.ItemTouchHelperCallback

class Hw6FragmentNotesRecycler : BaseFragment<FragmentHw6NotesRecyclerBinding>() {

    override fun inflateViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentHw6NotesRecyclerBinding {
        return FragmentHw6NotesRecyclerBinding.inflate(inflater, container, false)
    }

    val data = arrayListOf(
        DataNote("1", type = TYPE_NOTE),
        DataNote("2", type = TYPE_NOTE),
        DataNote("3", type = TYPE_NOTE)
    )

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val adapter = Hw6NotesAdapter(data,
            object : NoteClickListener {
                override fun onClick(position: Int) {
                    Toast.makeText(
                        requireContext(),
                        "Works ${data[position].noteTitle} ${data[position].noteDescription}",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            })
        binding.recyclerView.adapter = adapter
        binding.recyclerActivityFAB.setOnClickListener {
            adapter.appendItem()
        }
        ItemTouchHelper(ItemTouchHelperCallback(adapter)).attachToRecyclerView(binding.recyclerView)
    }

}