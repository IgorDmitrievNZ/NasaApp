package com.example.android.nasaapp.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.android.nasaapp.databinding.ActivityMainBinding
import com.example.android.nasaapp.ui.picture.PictureOfTheDayFragment

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(
                    com.example.android.nasaapp.R.id.container,
                    PictureOfTheDayFragment.newInstance()
                ).commit()
        }
    }
}