package com.example.googlemap

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.googlemap.Adapter.MountianSecondAdapter
import com.example.googlemap.ModelClass.MountianModelClass
import com.example.googlemap.databinding.ActivityMountianBinding
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class MountianActivity : AppCompatActivity() {
    lateinit var Binding : ActivityMountianBinding
    lateinit var MyAdapter: MountianSecondAdapter

    var reference = FirebaseDatabase.getInstance().reference
    var list: ArrayList<MountianModelClass> = ArrayList()


    override fun onCreate(savedInstanceState: Bundle?) {
        Binding = ActivityMountianBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(Binding.root)

        initview()
    }

    private fun initview() {

        reference.root.child("MountainTb").addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                for (child in snapshot.children) {
                    var data: MountianModelClass? = child.getValue(MountianModelClass::class.java)
//                    list.add(data as MountianModelClass)

                    if (data != null) {
                        list.add(data)
                    }
                }
                var MyAdapter = MountianSecondAdapter(this@MountianActivity,list)
                var manager = LinearLayoutManager(
                    this@MountianActivity, LinearLayoutManager.VERTICAL, false)
                Binding.rcvMountion.adapter = MyAdapter
                Binding.rcvMountion.layoutManager = manager
            }
            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }
        })

    }
}