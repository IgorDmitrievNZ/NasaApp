package com.example.android.nasaapp.ui.lesson6_recycler

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.android.nasaapp.BaseFragment
import com.example.android.nasaapp.databinding.FragmentLesson6Binding
import com.example.android.nasaapp.utils.openFragment

class Lesson6Fragment : BaseFragment<FragmentLesson6Binding>() {

    override fun inflateViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentLesson6Binding {
        return FragmentLesson6Binding.inflate(inflater, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btnRecyclerView.setOnClickListener {
            openFragment(requireActivity(), Lesson6RecyclerFragment(), "")
        }
    }
}