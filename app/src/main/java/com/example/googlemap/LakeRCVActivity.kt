package com.example.googlemap

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.googlemap.Adapter.LakeSecondAdapter
import com.example.googlemap.ModelClass.LakeModelClass
import com.example.googlemap.databinding.ActivityLakeRcvactivityBinding
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class LakeRCVActivity : AppCompatActivity() {

    lateinit var Binding : ActivityLakeRcvactivityBinding
    var reference = FirebaseDatabase.getInstance().reference
    var list: ArrayList<LakeModelClass> = ArrayList()
    lateinit var MyAdapter: LakeSecondAdapter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Binding = ActivityLakeRcvactivityBinding.inflate(layoutInflater)
        setContentView(Binding.root)

        initview()
    }

    private fun initview() {

        reference.root.child("LakeTb").addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                for (child in snapshot.children) {
                    var data: LakeModelClass? = child.getValue(LakeModelClass::class.java)
//                    list.add(data as MountianModelClass)

                    if (data != null) {
                        list.add(data)
                    }
                }
                var MyAdapter = LakeSecondAdapter(this@LakeRCVActivity,list)
                var manager = LinearLayoutManager(
                    this@LakeRCVActivity, LinearLayoutManager.VERTICAL, false)
                Binding.rcvLake.adapter = MyAdapter
                Binding.rcvLake.layoutManager = manager
            }
            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }
        })

    }
}