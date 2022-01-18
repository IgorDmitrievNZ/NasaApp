package com.example.android.nasaapp.ui.lesson8

import android.view.LayoutInflater
import android.view.ViewGroup
import com.example.android.nasaapp.BaseFragment
import com.example.android.nasaapp.databinding.FragmentTestTextBinding

class TestTextFragment : BaseFragment<FragmentTestTextBinding>() {

    override fun inflateViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentTestTextBinding {
        return FragmentTestTextBinding.inflate(inflater, container, false)
    }

}