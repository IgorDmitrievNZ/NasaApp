package com.example.android.nasaapp.ui.animations_lesson5

import android.os.Bundle
import android.view.Gravity
import android.view.View
import android.widget.FrameLayout
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.transition.*
import com.example.android.nasaapp.R
import com.example.android.nasaapp.databinding.ActivityAnimationsBinding
import com.example.android.nasaapp.ui.animations_lesson5.recycler.RecyclerAnimationFragment

class AnimationsActivity : AppCompatActivity() {

    private lateinit var binding: ActivityAnimationsBinding

    private var isTextViewVisible = false
    private var isExpand = false
    private var isDirectionRight = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAnimationsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //on click listener for blowing up the picture
        binding.imageView.setOnClickListener {
            isExpand = !isExpand

            val params = binding.imageView.layoutParams as FrameLayout.LayoutParams

            val transitionSet = TransitionSet()
            val transitionCB = ChangeBounds()
            val transitionImage = ChangeImageTransform()
            transitionCB.duration = 2000
            transitionImage.duration = 2000
            transitionSet.addTransition(transitionCB)
            transitionSet.addTransition(transitionImage)
            TransitionManager.beginDelayedTransition(binding.container, transitionSet)

            if (isExpand) {
                binding.imageView.scaleType = ImageView.ScaleType.CENTER_CROP
                params.height = FrameLayout.LayoutParams.MATCH_PARENT
            } else {
                binding.imageView.scaleType = ImageView.ScaleType.CENTER_INSIDE
                params.height = FrameLayout.LayoutParams.WRAP_CONTENT
            }
        }

        //click listener for bottom navigation
        binding.bottomNavigationView.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.bottom_view_earth -> {
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.container, RecyclerAnimationFragment.newInstance()).commit()
                    true
                }
                R.id.bottom_view_mars -> {
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.container, Lesson5Fragment.newInstance()).commit()
                    true
                }
                R.id.bottom_view_system -> {
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.container, ObjectAnimatorFragment()).commit()
                    true
                }
                R.id.bottom_view_system2 -> {
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.container, StataListAnimatorFragment()).commit()
                    true
                }
                R.id.bottom_view_system3 -> {
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.container, ConstraintSetFragment()).commit()
                    true
                }
                else -> {
                    false
                }
            }
        }

        // click listener for button shows some animation
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

        // click listener for buttonPath shows animation using special trajectory
        binding.buttonPath.setOnClickListener {
            isDirectionRight = !isDirectionRight
            val params = binding.buttonPath.layoutParams as FrameLayout.LayoutParams
            params.gravity = if (isDirectionRight) {
                Gravity.CENTER or Gravity.END
            } else {
                Gravity.TOP or Gravity.START
            }
            val transition = ChangeBounds()
            val path = ArcMotion()
            //path.maximumAngle = 90f  //tuning the angle
            transition.setPathMotion(path)
            transition.duration = 3000
            TransitionManager.beginDelayedTransition(binding.container, transition)

            binding.buttonPath.layoutParams = params
        }
    }
}