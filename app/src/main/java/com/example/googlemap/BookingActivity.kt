package com.example.googlemap

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.googlemap.Adapter.BookingAdapter
import com.example.googlemap.databinding.ActivityBookingBinding
import com.google.android.material.tabs.TabLayout

class BookingActivity : AppCompatActivity() {
    lateinit var binding : ActivityBookingBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityBookingBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        initview()
    }
    private fun initview() {

//        tablaybou add

        binding.tablayout.addTab(binding.tablayout.newTab().setText("Hotel"))
        binding.tablayout.addTab(binding.tablayout.newTab().setText("Flight"))
        binding.tablayout.addTab(binding.tablayout.newTab().setText("Bus"))

        var adapter=BookingAdapter(supportFragmentManager,binding.tablayout.tabCount)
        binding.viewpager.adapter=adapter

        binding.viewpager.addOnPageChangeListener(TabLayout.TabLayoutOnPageChangeListener(binding.tablayout))


        binding.tablayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab?) {
                if (tab !=null)
                {
                    binding.viewpager.currentItem = tab.position
                }
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {
            }

            override fun onTabReselected(tab: TabLayout.Tab?) {
            }

        })
    }
}