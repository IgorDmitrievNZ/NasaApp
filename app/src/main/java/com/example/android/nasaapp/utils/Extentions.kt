package com.example.android.nasaapp.utils

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import com.example.android.nasaapp.R


fun openFragment(requireActivity: FragmentActivity, fragment: Fragment, backstack: String) {
    requireActivity.supportFragmentManager.beginTransaction().replace(
        R.id.container,
        fragment,
    ).addToBackStack(backstack)
        .commit()
}
