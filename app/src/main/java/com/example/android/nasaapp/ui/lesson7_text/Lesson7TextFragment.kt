package com.example.android.nasaapp.ui.lesson7_text

import android.os.Build
import android.os.Bundle
import android.text.Spannable
import android.text.SpannableString
import android.text.SpannableStringBuilder
import android.text.style.BulletSpan
import android.text.style.ImageSpan
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.android.nasaapp.BaseFragment
import com.example.android.nasaapp.R
import com.example.android.nasaapp.databinding.FragmentLeson7TextBinding

class Lesson7TextFragment : BaseFragment<FragmentLeson7TextBinding>() {

    override fun inflateViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentLeson7TextBinding {
        return FragmentLeson7TextBinding.inflate(inflater, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val spannableMutable = SpannableStringBuilder("My \n text \n text \nbullet one \nbullet two")
        val spannableUnMutable = SpannableString("My text \nbullet one \nbullet two")

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
            spannableMutable.setSpan(
                BulletSpan(20, resources.getColor(R.color.colorAccent), 20),
                0, 30, Spannable.SPAN_INCLUSIVE_INCLUSIVE
            )
            spannableMutable.setSpan(
                BulletSpan(20, resources.getColor(R.color.colorAccent), 20),
                4, 21, Spannable.SPAN_INCLUSIVE_INCLUSIVE
            )
            spannableMutable.setSpan(
                BulletSpan(20, resources.getColor(R.color.colorAccent), 20),
                11, 21, Spannable.SPAN_INCLUSIVE_INCLUSIVE
            )
        }

        spannableMutable.setSpan(BulletSpan(20,resources.getColor(R.color.colorAccent)),
            21,spannableMutable.length,Spannable.SPAN_INCLUSIVE_INCLUSIVE)

        for (i in spannableMutable.indices) {
            if (spannableMutable[i] == 'o') {
                spannableMutable.setSpan(ImageSpan(requireContext(), R.drawable.ic_earth), i, i + 1, Spannable.SPAN_INCLUSIVE_INCLUSIVE)
            }
        }

        binding.textView.text = spannableMutable
    }
}