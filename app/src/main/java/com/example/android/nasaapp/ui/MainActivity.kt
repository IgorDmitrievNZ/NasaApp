package com.example.android.nasaapp.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.android.nasaapp.R
import com.example.android.nasaapp.databinding.ActivityMainBinding
import com.example.android.nasaapp.ui.picture.PictureOfTheDayFragment

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        setTheme(R.style.Theme_NasaApp)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(
                    R.id.container,
                    PictureOfTheDayFragment.newInstance()
                ).commit()
        }
    }
}