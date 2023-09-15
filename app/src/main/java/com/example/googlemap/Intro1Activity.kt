package com.example.googlemap

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.googlemap.databinding.ActivityIntro1Binding

class Intro1Activity : AppCompatActivity() {
    lateinit var Binding : ActivityIntro1Binding
    override fun onCreate(savedInstanceState: Bundle?) {
        Binding = ActivityIntro1Binding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(Binding.root)

        initview()
    }

    private fun initview() {

        Binding.Intro1.setOnClickListener {

            var i = Intent(this,Intro2Activity::class.java)
            startActivity(i)
        }
    }
}