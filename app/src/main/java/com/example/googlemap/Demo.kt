package com.example.googlemap

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.googlemap.databinding.ActivityDemoBinding

class Demo : AppCompatActivity() {
    lateinit var Binding : ActivityDemoBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        Binding = ActivityDemoBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(Binding.root)

        initview()
    }

    private fun initview() {

        Binding.Addmeen.setOnClickListener {

            var i = Intent(this,Demo2::class.java)
            startActivity(i)
        }
    }
}