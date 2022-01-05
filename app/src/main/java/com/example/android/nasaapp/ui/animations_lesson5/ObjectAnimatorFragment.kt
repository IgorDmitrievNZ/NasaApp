package com.example.android.nasaapp.ui.animations_lesson5

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.animation.ObjectAnimator
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.android.nasaapp.BaseFragment
import com.example.android.nasaapp.databinding.FragmentObjectAnimatorBinding

private const val duration = 1000L

class ObjectAnimatorFragment : BaseFragment<FragmentObjectAnimatorBinding>() {

    var isExpanded = false

    override fun inflateViewBinding(inflater: LayoutInflater, container: ViewGroup?): FragmentObjectAnimatorBinding {
        return FragmentObjectAnimatorBinding.inflate(inflater,container,false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) = with(binding) {
        super.onViewCreated(view, savedInstanceState)

        transparentBackground.alpha =0f
        optionOneContainer.alpha =0f
        optionOneContainer.isClickable = false
        optionTwoContainer.alpha =0f
        optionTwoContainer.isClickable = false

        fab.setOnClickListener {
            if (isExpanded) {
                isExpanded = false
                ObjectAnimator.ofFloat(plusImageview, "rotation", 315f, 0f)
                    .setDuration(duration).start()
                ObjectAnimator.ofFloat(optionOneContainer, "translationY", 0f)
                    .setDuration(duration).start()
                ObjectAnimator.ofFloat(optionTwoContainer, "translationY", 0f)
                    .setDuration(duration).start()
                optionOneContainer.animate()
                    .alpha(0f)
                    .setDuration(duration)
                    .setListener(object : AnimatorListenerAdapter() {
                        override fun onAnimationEnd(animation: Animator?) {
                            super.onAnimationEnd(animation)
                            optionOneContainer.isClickable = false
                        }
                    })
                optionTwoContainer.animate()
                    .alpha(0f)
                    .setDuration(duration)
                    .setListener(object : AnimatorListenerAdapter() {
                        override fun onAnimationEnd(animation: Animator?) {
                            super.onAnimationEnd(animation)
                            optionTwoContainer.isClickable = false
                        }
                    })
                transparentBackground.animate()
                    .alpha(0f)
                    .setDuration(duration)
            } else {
                isExpanded = true
                ObjectAnimator.ofFloat(binding.plusImageview, "rotation", 0f, 315f)
                    .setDuration(duration).start()
                ObjectAnimator.ofFloat(binding.optionOneContainer, "translationY", -300f)
                    .setDuration(duration).start()
                ObjectAnimator.ofFloat(binding.optionTwoContainer, "translationY", -150f)
                    .setDuration(duration).start()
                binding.optionOneContainer.animate()
                    .alpha(1f)
                    .setDuration(duration)
                    .setListener(object : AnimatorListenerAdapter() {
                        override fun onAnimationEnd(animation: Animator?) {
                            super.onAnimationEnd(animation)
                            binding.optionOneContainer.isClickable = true
                        }
                    })
                binding.optionTwoContainer.animate()
                    .alpha(1f)
                    .setDuration(duration)
                    .setListener(object : AnimatorListenerAdapter() {
                        override fun onAnimationEnd(animation: Animator?) {
                            super.onAnimationEnd(animation)
                            binding.optionTwoContainer.isClickable = true
                        }
                    })

                binding.transparentBackground.animate()
                    .alpha(0.4f)
                    .setDuration(0)
            }
        }
    }
}