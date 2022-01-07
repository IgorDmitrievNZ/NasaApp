package com.example.android.nasaapp.ui.lesson6_recycler

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.ItemTouchHelper
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
            Data("Earth", type = TYPE_EARTH) to false,
            Data("Mars", "", type = TYPE_MARS) to false/*,
            Data("Earth", type = TYPE_EARTH),
            Data("Mars", "", type = TYPE_MARS),
            Data("Earth", type = TYPE_EARTH),
            Data("Earth", type = TYPE_EARTH),
            Data("Earth", type = TYPE_EARTH),
            Data("Mars", null, type = TYPE_MARS)*/
        )
        data.add(0, Data("Title", type = TYPE_HEADER) to false)

//  ************************************************************************************************
        val lat = 55
        val lon = 37
        val coordinate1 = lat to lon
        val coordinate2 = Pair(lat, lon)
        coordinate1.first
        coordinate1.second
        val coordinate3d = Triple(1, 2, 3)  // just an example
        coordinate3d.first                                     // this section is showing how Pair works
        coordinate3d.second
        coordinate3d.third
// *************************************************************************************************

        val adapter = RecyclerLesson6Adapter(data,
            object : MyCallback {
                override fun onClick(position: Int) {
                    Toast.makeText(
                        requireContext(),
                        "Works ${data[position].first.someText} ${data[position].first.someDescription}",
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