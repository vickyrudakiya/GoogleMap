package com.example.googlemap

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.googlemap.databinding.ActivityNusaPedingBinding

class NusaPeding : AppCompatActivity() {
    lateinit var Binding : ActivityNusaPedingBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        Binding = ActivityNusaPedingBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(Binding.root)
    }
}