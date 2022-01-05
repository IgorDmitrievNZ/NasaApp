package com.example.android.nasaapp.ui.animations_lesson5

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnticipateOvershootInterpolator
import androidx.constraintlayout.widget.ConstraintSet
import androidx.transition.ChangeBounds
import androidx.transition.TransitionManager
import com.example.android.nasaapp.BaseFragment
import com.example.android.nasaapp.R
import com.example.android.nasaapp.databinding.FragmentConstraintSetBinding

class ConstraintSetFragment : BaseFragment<FragmentConstraintSetBinding>() {

    override fun inflateViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentConstraintSetBinding {
        return FragmentConstraintSetBinding.inflate(inflater,container,false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.root.setOnClickListener {
            val constraintSetStart = ConstraintSet()
            constraintSetStart.clone(requireContext(), R.layout.fragment_constraint_set)

            //constraintSetStart.connect(R.id.title,ConstraintSet.END,R.id.backgroundImage,ConstraintSet.END)  //
           // constraintSetStart.connect(R.id.description,ConstraintSet.TOP,R.id.title,ConstraintSet.BOTTOM)

            val constraintSetEnd = ConstraintSet()
            constraintSetEnd.clone(requireContext(),R.layout.fragment_constraint_set_end)

            val transition = ChangeBounds()
            transition.duration = 1000
            transition.interpolator = AnticipateOvershootInterpolator(2f)
            TransitionManager.beginDelayedTransition(binding.root,transition)
            //constraintSetStart.applyTo(binding.constraintContainer)
            constraintSetEnd.applyTo(binding.constraintContainer)
        }
    }
}