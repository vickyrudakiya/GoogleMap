package com.example.googlemap

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.googlemap.databinding.ActivityIntro2Binding

class Intro2Activity : AppCompatActivity() {
    lateinit var Binding : ActivityIntro2Binding
    override fun onCreate(savedInstanceState: Bundle?) {
        Binding = ActivityIntro2Binding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(Binding.root)

        initview()
    }

    private fun initview() {

        Binding.Intro2.setOnClickListener {

            var i = Intent(this,Intro3Activity::class.java)
            startActivity(i)
        }
    }
}