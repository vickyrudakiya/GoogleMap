package com.example.googlemap

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.googlemap.Adapter.BeachSecondAdapter
import com.example.googlemap.ModelClass.BeachModelClass
import com.example.googlemap.databinding.ActivityBeachRcvactivityBinding
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class BeachRCVActivity() : AppCompatActivity() {
    lateinit var Binding : ActivityBeachRcvactivityBinding
    lateinit var MyAdapter: BeachSecondAdapter
    var reference = FirebaseDatabase.getInstance().reference
    var list: ArrayList<BeachModelClass> = ArrayList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Binding = ActivityBeachRcvactivityBinding.inflate(layoutInflater)
        setContentView(Binding.root)

        initview()
    }

    private fun initview() {

        reference.root.child("BeachTb").addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                for (child in snapshot.children) {
                    var data: BeachModelClass? = child.getValue(BeachModelClass::class.java)
//                    list.add(data as MountianModelClass)

                    if (data != null) {
                        list.add(data)
                    }
                }
                var MyAdapter = BeachSecondAdapter(this@BeachRCVActivity, list)
                var manager = LinearLayoutManager(
                    this@BeachRCVActivity, LinearLayoutManager.VERTICAL, false)
                Binding.rcvBeach.adapter = MyAdapter
                Binding.rcvBeach.layoutManager = manager
            }
            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }
        })

    }
}