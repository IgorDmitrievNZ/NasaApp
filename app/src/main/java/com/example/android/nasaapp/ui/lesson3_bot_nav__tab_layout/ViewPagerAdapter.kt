package com.example.android.nasaapp.ui.lesson3_bot_nav__tab_layout

import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter

//private const val EARTH_FRAGMENT = 0
//private const val MARS_FRAGMENT = 1      //old way
//private const val SYSTEM_FRAGMENT = 2

class ViewPagerAdapter(fa: FragmentActivity) : FragmentStateAdapter(fa) {

//    private val fragments = arrayOf(EarthFragment(), MarsFragment(), SystemFragment())

//    override fun getCount() = fragments.size
//
//    override fun getItem(position: Int): Fragment {
//        return when (position) {
//            EARTH_FRAGMENT -> fragments[EARTH_FRAGMENT]
//            MARS_FRAGMENT -> fragments[MARS_FRAGMENT]
//            SYSTEM_FRAGMENT -> fragments[SYSTEM_FRAGMENT]
//            else -> fragments[EARTH_FRAGMENT]
//        }
//    }

    //  исправить неточности lesson 3 3:03:00
    /* override fun getPageTitle(position: Int): CharSequence? {
         return when (position) {
             EARTH_FRAGMENT -> "EARTH"
             MARS_FRAGMENT -> "MARS"
             SYSTEM_FRAGMENT -> "SYSTEM"
             else -> "EARTH"
         }
     }*/

    private val fragments = arrayOf(EarthFragment(), MarsFragment(), SystemFragment())

    override fun getItemCount() = fragments.size

    override fun createFragment(position: Int) = fragments[position]


}