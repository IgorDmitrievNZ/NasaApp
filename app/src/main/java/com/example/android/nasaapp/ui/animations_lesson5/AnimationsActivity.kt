package com.example.android.nasaapp.ui.animations_lesson5

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.transition.ChangeBounds
import androidx.transition.Fade
import androidx.transition.TransitionManager
import androidx.transition.TransitionSet
import com.example.android.nasaapp.R
import com.example.android.nasaapp.databinding.ActivityAnimationsBinding
import com.example.android.nasaapp.ui.animations_lesson5.recycler.RecyclerAnimationFragment

class AnimationsActivity : AppCompatActivity() {

    private lateinit var binding: ActivityAnimationsBinding

    private var isTextViewVisible = false
    private var isButtonViewVisible = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAnimationsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //click listener for bottom navigation
        binding.bottomNavigationView.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.bottom_view_earth -> {
                    isButtonViewVisible = false
                    binding.button.visibility = if (isButtonViewVisible) View.VISIBLE else View.GONE
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.container, RecyclerAnimationFragment.newInstance()).commit()
                    true
                }
                R.id.bottom_view_mars -> {

                    true
                }
                R.id.bottom_view_system -> {

                    true
                }
                else -> {
                    false
                }
            }
        }

        binding.button.setOnClickListener {
            isTextViewVisible = !isTextViewVisible

//            val transition = AutoTransition()
//            transition.duration = 2000
//            /* аналогичные команды */
//            TransitionManager.beginDelayedTransition(binding.transitionsContainer)
//            TransitionManager.beginDelayedTransition(binding.transitionsContainer,AutoTransition())
//            /* аналогичная команда, но время анимации изменено на 2 сек */

            var transition = TransitionSet()// исчезновение
            transition.addTransition(Fade())
            transition.addTransition(ChangeBounds())
//            transition.ordering = TransitionSet.ORDERING_SEQUENTIAL // указали последовательное выполнение
            transition.duration = 2000
            TransitionManager.beginDelayedTransition(binding.transitionsContainer, transition)
            binding.text.visibility = if (isTextViewVisible) View.VISIBLE else View.GONE
        }
    }
}