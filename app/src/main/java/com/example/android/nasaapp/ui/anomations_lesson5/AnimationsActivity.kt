package com.example.android.nasaapp.ui.anomations_lesson5

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.transition.AutoTransition
import androidx.transition.TransitionManager
import com.example.android.nasaapp.databinding.ActivityAnimationsBinding

class AnimationsActivity : AppCompatActivity() {

    private lateinit var binding: ActivityAnimationsBinding

    private var isTextViewVisible = false
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityAnimationsBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.button.setOnClickListener {
            isTextViewVisible = !isTextViewVisible
            val transition = AutoTransition()
            transition.duration = 2000
            /* аналогичные команды */
            TransitionManager.beginDelayedTransition(binding.transitionsContainer)
            TransitionManager.beginDelayedTransition(binding.transitionsContainer,AutoTransition())
            /* аналогичная команда, но время анимации изменено на 2 сек */
            TransitionManager.beginDelayedTransition(binding.transitionsContainer,transition)
            binding.text.visibility = if(isTextViewVisible) View.VISIBLE else View.GONE
        }
    }
}