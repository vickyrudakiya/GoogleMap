package com.example.googlemap

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.googlemap.databinding.ActivityPaymentDetailsBinding

class PaymentDetailsActivity : AppCompatActivity() {
    lateinit var Binding : ActivityPaymentDetailsBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        Binding = ActivityPaymentDetailsBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(Binding.root)

        initview()
    }

    private fun initview() {

    }
}