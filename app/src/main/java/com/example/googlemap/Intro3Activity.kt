package com.example.googlemap

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.googlemap.databinding.ActivityIntro3Binding

class Intro3Activity : AppCompatActivity() {
    lateinit var Binding : ActivityIntro3Binding
    override fun onCreate(savedInstanceState: Bundle?) {
        Binding = ActivityIntro3Binding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(Binding.root)

        initview()
    }

    private fun initview() {

        Binding.Intro3.setOnClickListener {

            var i = Intent(this,MainActivity::class.java)
            startActivity(i)
        }
    }
}