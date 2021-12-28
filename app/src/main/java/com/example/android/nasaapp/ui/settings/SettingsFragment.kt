package com.example.android.nasaapp.ui.settings

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatDelegate
import androidx.fragment.app.Fragment
import com.example.android.nasaapp.R
import com.example.android.nasaapp.databinding.FragnentSettingsBinding

class SettingsFragment : Fragment() {

    private var _binding: FragnentSettingsBinding? = null
    private val binding: FragnentSettingsBinding
        get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragnentSettingsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) = with(binding) {
        super.onViewCreated(view, savedInstanceState)

        chipGroupThemes.setOnCheckedChangeListener { _, checkedId ->
            when (checkedId) {
                R.id.mars -> {
                  //  TODO work with themes implementation SHARED PREFERENCE etc
//                    parentActivity.setCurrentTheme(R.style.ThemeMars)
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
                    requireActivity().setTheme(R.style.ThemeMars)
//                    parentActivity.recreate()
                }
                R.id.night -> {
                    requireActivity().setTheme(R.style.Theme_NasaApp)
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
                }
                R.id.day -> {
                    requireActivity().setTheme(R.style.Theme_NasaApp)
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
//                    parentActivity.setCurrentTheme(R.style.Theme_NasaApp)
//                    parentActivity.recreate()

                }
            }
        }

//
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    companion object {
        @JvmStatic
        fun newInstance() = SettingsFragment()
    }
}