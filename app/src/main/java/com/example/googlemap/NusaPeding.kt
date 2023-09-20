package com.example.googlemap

import android.content.Context
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.bumptech.glide.Glide
import com.example.googlemap.Adapter.BeachSecondAdapter
import com.example.googlemap.ModelClass.ForestModelClass
import com.example.googlemap.databinding.ActivityNusaPedingBinding
import com.google.firebase.database.FirebaseDatabase

class NusaPeding : AppCompatActivity() {
    var list : ArrayList<BeachSecondAdapter> = ArrayList()
    lateinit var Binding : ActivityNusaPedingBinding
    var key : String=" "

    override fun onCreate(savedInstanceState: Bundle?) {
        Binding = ActivityNusaPedingBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(Binding.root)

        initview()
    }

    private fun initview() {


        if(intent!=null)
        {
              var img  = intent.getStringExtra("Image").toString()


            Log.e("TAG", "image uri ==> : "+img )

            key = intent.getStringExtra("key").toString()


            Glide.with(baseContext).load(img).into(Binding.imgLoad)

        }



    }
}