package com.example.googlemap

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.googlemap.Adapter.ForestSecondAdapter
import com.example.googlemap.Adapter.LakeSecondAdapter
import com.example.googlemap.ModelClass.ForestModelClass
import com.example.googlemap.databinding.ActivityForestRcvactivityBinding
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class ForestRCVActivity : AppCompatActivity() {
    lateinit var Binding: ActivityForestRcvactivityBinding
    var reference = FirebaseDatabase.getInstance().reference
    var list: ArrayList<ForestModelClass> = ArrayList()
    lateinit var MyAdapter: ForestSecondAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Binding = ActivityForestRcvactivityBinding.inflate(layoutInflater)
        setContentView(Binding.root)

        initview()
    }

    private fun initview() {

        reference.root.child("ForestTb").addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                for (child in snapshot.children) {
                    var data: ForestModelClass? = child.getValue(ForestModelClass::class.java)
//                    list.add(data as MountianModelClass)

                    if (data != null) {
                        list.add(data)
                    }
                    MyAdapter = ForestSecondAdapter(this@ForestRCVActivity, list,
                        onitemclick = { Image ->


                            Log.e("TAG", "onDataChange: " + Image)

                            var i = Intent(this@ForestRCVActivity, NusaPeding::class.java)
                            i.putExtra("Image", Image)
                            startActivity(i)


                        }
                    )

                }



                var manager = LinearLayoutManager(
                    this@ForestRCVActivity, LinearLayoutManager.VERTICAL, false
                )
                Binding.rcvForest.adapter = MyAdapter
                Binding.rcvForest.layoutManager = manager


            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }
        })

    }
}