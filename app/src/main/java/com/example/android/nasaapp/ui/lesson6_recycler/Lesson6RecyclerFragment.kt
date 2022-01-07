package com.example.android.nasaapp.ui.lesson6_recycler

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.example.android.nasaapp.BaseFragment
import com.example.android.nasaapp.databinding.FragmentLesson6RecyclerBinding

class Lesson6RecyclerFragment : BaseFragment<FragmentLesson6RecyclerBinding>() {

    override fun inflateViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentLesson6RecyclerBinding {
        return FragmentLesson6RecyclerBinding.inflate(inflater, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val data = arrayListOf(
            Data("Earth", type = TYPE_EARTH)/*,
            Data("Earth", type = TYPE_EARTH),
            Data("Mars", "", type = TYPE_MARS),
            Data("Earth", type = TYPE_EARTH),
            Data("Earth", type = TYPE_EARTH),
            Data("Earth", type = TYPE_EARTH),
            Data("Mars", null, type = TYPE_MARS)*/
        )
        data.add(0,Data("Title",type= TYPE_HEADER))

        val adapter = RecyclerLesson6Adapter(data,
            object : MyCallback {
                override fun onClick(position: Int) {
                    Toast.makeText(
                        requireContext(),
                        "Works ${data[position].someText} ${data[position].someDescription}",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            })
        binding.recyclerView.adapter = adapter
        binding.recyclerActivityFAB.setOnClickListener {
            adapter.appendItem()
        }
    }
}