package com.example.android.nasaapp.ui.animations_lesson5

import android.os.Bundle
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.view.ViewCompat
import androidx.fragment.app.Fragment
import androidx.transition.ChangeBounds
import androidx.transition.TransitionManager
import com.example.android.nasaapp.databinding.FragmentLesson5Binding

class Lesson5Fragment : Fragment() {

    private var _binding: FragmentLesson5Binding? = null
    private val binding: FragmentLesson5Binding
        get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentLesson5Binding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) = with(binding) {
        super.onViewCreated(view, savedInstanceState)

        val titles: MutableList<String> = ArrayList()
        for (i in 0..4) {
            titles.add("Item $i")
        }
        binding.button.setOnClickListener {
            val cb = ChangeBounds()
            cb.duration = 2000
            TransitionManager.beginDelayedTransition(binding.transitionsContainer, cb)
            titles.shuffle()

            binding.transitionsContainer.removeAllViews()
            titles.forEach {
                binding.transitionsContainer.addView(TextView(requireContext()).apply {
                    text = it
                    ViewCompat.setTransitionName(this, it)
                    gravity = Gravity.CENTER_HORIZONTAL
                })
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    companion object {
        @JvmStatic
        fun newInstance() = Lesson5Fragment()
    }
}