package com.example.googlemap.Adapter


import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.example.googlemap.Fragment.BusFragment
import com.example.googlemap.Fragment.FlightFragment
import com.example.googlemap.Fragment.Hotelfragument

class BookingAdapter(supportFragmentManager: FragmentManager, val tabCount: Int) :
    FragmentPagerAdapter(supportFragmentManager) {

    override fun getCount(): Int {
        return tabCount
    }

    override fun getItem(position: Int): Fragment {

        when (position) {
            0 -> {
                return Hotelfragument()
            }
            1 -> {
                return FlightFragment()
            }
            2 -> {
                return BusFragment()
            }
            else -> {
                return FlightFragment()

            }
        }
    }
}