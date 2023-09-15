package com.example.googlemap

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.example.googlemap.Fragment.BookingFragment
import com.example.googlemap.Fragment.MapFragument
import com.example.googlemap.Fragment.ProfileFragment
import com.example.googlemap.Fragment.favouriteFragment
import com.example.googlemap.databinding.ActivityBottamBinding

class BottamActivity : AppCompatActivity() {
    lateinit var Binding : ActivityBottamBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        Binding = ActivityBottamBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(Binding.root)

        initview()
    }

    private fun initview() {
        Binding.bottamNavigation.setOnItemSelectedListener {

            if (it.itemId == R.id.add_Explore) {
                loadfragement(HomeFragment())
            } else if (it.itemId == R.id.add_Booking) {
                loadfragement(BookingFragment())
            }
            else if (it.itemId == R.id.add_Trips) {
                loadfragement(MapFragument())
            }
            else if (it.itemId == R.id.add_Favorites) {
                loadfragement(favouriteFragment())
            }
            else if (it.itemId == R.id.add_Profile) {
                loadfragement(ProfileFragment())
            }
            return@setOnItemSelectedListener true
        }
    }
    fun loadfragement(ff: Fragment) {

        val fragmentManager = supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.contener, ff)
        fragmentTransaction.commit()

    }

}
