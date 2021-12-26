package com.example.android.nasaapp.ui.lesson3_bot_nav__tab_layout

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.android.nasaapp.R
import com.example.android.nasaapp.databinding.ActivityViewPagerBinding
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator

class ViewPagerActivity : AppCompatActivity() {
    lateinit var binding: ActivityViewPagerBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityViewPagerBinding.inflate(layoutInflater)
        setContentView(binding.root)

                    //old view pager
       // binding.viewPager.adapter = ViewPagerAdapter(supportFragmentManager)
       // binding.tabLayout.setupWithViewPager(binding.viewPager)

//       binding.tabLayout.getTabAt(0)?.setIcon(R.drawable.ic_earth)
//        binding.tabLayout.getTabAt(1)?.setIcon(R.drawable.ic_mars)
//        binding.tabLayout.getTabAt(2)?.setIcon(R.drawable.ic_system)

                    //new view pager
        binding.viewPager.adapter = ViewPagerAdapter(this)
        TabLayoutMediator(binding.tabLayout,binding.viewPager, object : TabLayoutMediator.TabConfigurationStrategy{
            override fun onConfigureTab(tab: TabLayout.Tab, position: Int) {

            }
        }).attach()

        setCustomTabs()
    }

    private fun setCustomTabs() {
        binding.tabLayout.getTabAt(0)?.customView =
            layoutInflater.inflate(R.layout.activity_view_pager_tab_item_earth, null)
        binding.tabLayout.getTabAt(1)?.customView =
            layoutInflater.inflate(R.layout.activity_view_pager_tab_item_mars, null)
        binding.tabLayout.getTabAt(2)?.customView =
            layoutInflater.inflate(R.layout.activity_view_pager_tab_item_system, null)
    }
}