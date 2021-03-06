package com.example.android.nasaapp.ui.mars

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.android.nasaapp.R
import com.example.android.nasaapp.databinding.ActivityBottomNavigationMarsBinding
import com.example.android.nasaapp.ui.mars.mars_weather.MarsWeatherFragment
import com.example.android.nasaapp.ui.mars.planets.PlanetsFragment

class NavBottomActivityMars : AppCompatActivity() {

    lateinit var binding: ActivityBottomNavigationMarsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityBottomNavigationMarsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //opens default fragment in activity
        supportFragmentManager.beginTransaction()
            .replace(R.id.container, MarsHomeWorkFragment.newInstance()).commit()

        //click listener for bottom navigation
        binding.bottomNavigationViewMars.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.bottomViewMarsPhotos -> {
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.container, MarsHomeWorkFragment.newInstance()).commit()
                    true
                }
                R.id.bottomViewCurrentWeather -> {
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.container, MarsWeatherFragment.newInstance()).commit()
                    true
                }
                R.id.bottomViewPlanets -> {
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.container, PlanetsFragment.newInstance()).commit()
                    true
                }
                else -> {
                    false
                }
            }
        }
    }
}