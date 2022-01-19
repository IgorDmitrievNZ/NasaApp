package com.example.android.nasaapp.ui.lesson8

import android.view.LayoutInflater
import android.view.ViewGroup
import com.example.android.nasaapp.BaseFragment
import com.example.android.nasaapp.databinding.FragmentLesson8TestButtonBinding

class Lesson8TestTextFragment : BaseFragment<FragmentLesson8TestButtonBinding>() {

    override fun inflateViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentLesson8TestButtonBinding {
        return FragmentLesson8TestButtonBinding.inflate(inflater, container, false)
    }

}