package com.example.android.nasaapp.ui.lesson4_coordinator

import android.content.Context
import android.util.AttributeSet
import android.view.View
import androidx.coordinatorlayout.widget.CoordinatorLayout
import com.google.android.material.appbar.AppBarLayout
import kotlin.math.abs


class ButtonBehavior(context: Context, attr: AttributeSet) :
    CoordinatorLayout.Behavior<View>(context, attr) {

    override fun layoutDependsOn(
        parent: CoordinatorLayout,
        child: View,
        dependency: View
    ): Boolean {
        return (dependency is AppBarLayout)
    }

    override fun onDependentViewChanged(
        parent: CoordinatorLayout,
        child: View,
        dependency: View
    ): Boolean {

        val bar = dependency as AppBarLayout
        val height = bar.height
        if (abs(bar.y) > (height / 2)) {
            child.visibility = View.GONE
        } else {
            child.visibility = View.VISIBLE
            child.alpha = (height / 2 - abs(bar.y)) / (height / 2)
        }
        //Log.d("mylogs","${bar.y} bar.height")

        return super.onDependentViewChanged(parent, child, dependency)
    }
}