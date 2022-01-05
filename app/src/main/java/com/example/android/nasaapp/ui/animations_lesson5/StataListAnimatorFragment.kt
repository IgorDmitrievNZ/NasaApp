package com.example.android.nasaapp.ui.animations_lesson5

import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import com.example.android.nasaapp.BaseFragment
import com.example.android.nasaapp.databinding.FragmentStateListAnimatorBinding

class StataListAnimatorFragment : BaseFragment<FragmentStateListAnimatorBinding>() {

    override fun inflateViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentStateListAnimatorBinding {
        return FragmentStateListAnimatorBinding.inflate(inflater, container, false)
    }

    @RequiresApi(Build.VERSION_CODES.M)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.scrollView.setOnScrollChangeListener { v, scrollX, scrollY, oldScrollX, oldScrollY ->
            binding.header.isSelected = binding.scrollView.canScrollVertically(-1)
        }
    }
}