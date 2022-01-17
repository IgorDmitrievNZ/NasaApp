package com.example.android.nasaapp.ui.lesson7_text

import android.os.Build
import android.os.Bundle
import android.text.Spannable
import android.text.SpannableString
import android.text.SpannableStringBuilder
import android.text.style.BulletSpan
import android.text.style.ForegroundColorSpan
import android.text.style.ImageSpan
import android.text.style.QuoteSpan
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.content.ContextCompat
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
                BulletSpan(20, ContextCompat.getColor(requireContext(),R.color.colorAccent), 20),
                0, 30, Spannable.SPAN_INCLUSIVE_INCLUSIVE
            )
            spannableMutable.setSpan(
                BulletSpan(20,ContextCompat.getColor(requireContext(),R.color.colorAccent), 20),
                4, 21, Spannable.SPAN_INCLUSIVE_INCLUSIVE
            )
            spannableMutable.setSpan(
                BulletSpan(20, ContextCompat.getColor(requireContext(),R.color.colorAccent), 20),
                11, 21, Spannable.SPAN_INCLUSIVE_INCLUSIVE
            )
        }

        spannableMutable.setSpan(BulletSpan(20,ContextCompat.getColor(requireContext(),R.color.colorAccent)),
            21,spannableMutable.length,Spannable.SPAN_INCLUSIVE_INCLUSIVE)

        for (i in spannableMutable.indices) {
            if (spannableMutable[i] == 'o') {
                spannableMutable.setSpan(ImageSpan(requireContext(), R.drawable.ic_earth), i, i + 1, Spannable.SPAN_INCLUSIVE_INCLUSIVE)
            }
        }

        binding.textView.text = spannableMutable

        val text = binding.textView2.text
        val spannableStart = SpannableStringBuilder(text)
        binding.textView2.setText(spannableStart,TextView.BufferType.EDITABLE)
        val spannable = binding.textView2.text as SpannableStringBuilder
        initSpans(spannable)

    }

    private fun initSpans(spannable: SpannableStringBuilder) {
        spannable.setSpan(
            ForegroundColorSpan(
                ContextCompat.getColor(
                    requireContext(),
                    R.color.colorAccent
                )
            ),
            0, 10, Spannable.SPAN_EXCLUSIVE_INCLUSIVE // FIXME EXCLUSIVE_INCLUSIVE
        )
        spannable.insert(0, "1")
        spannable.insert(10, "\n")
        spannable.insert(20, "\n")

        val q: QuoteSpan
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
            q = QuoteSpan(
                ContextCompat.getColor(
                    requireContext(),
                    R.color.colorAccent
                ),
                20,
                40
            )
        } else {
            q = QuoteSpan(
                ContextCompat.getColor(
                    requireContext(),
                    R.color.colorAccent
                )
            )
        }
        spannable.setSpan(
            q, 0, 20, Spannable.SPAN_EXCLUSIVE_INCLUSIVE // FIXME EXCLUSIVE_INCLUSIVE
        )
        val qq = QuoteSpan(
            ContextCompat.getColor(
                requireContext(),
                R.color.colorAccent
            )
        )
        spannable.setSpan(
            qq, 10, 19, Spannable.SPAN_EXCLUSIVE_INCLUSIVE // FIXME EXCLUSIVE_INCLUSIVE
        )
    }
}