package com.example.googlemap

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.googlemap.Adapter.HomeAdapter
import com.example.googlemap.ModelClass.ProfileModelClass
import com.example.googlemap.databinding.FragmentHomeBinding
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER

class HomeFragment : Fragment() {


    lateinit var Binding : FragmentHomeBinding
    var list: ArrayList<ProfileModelClass> = ArrayList()
    lateinit var MyAdapter: HomeAdapter
    var reference = FirebaseDatabase.getInstance().reference




    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        Binding = FragmentHomeBinding.inflate(inflater,container,false)



        Binding.rcvhome.setOnClickListener {

            reference.root.child("ProductTb").addValueEventListener(object : ValueEventListener {
                override fun onDataChange(snapshot: DataSnapshot) {

                    for (child in snapshot.children) {
                        var data: ProfileModelClass? = child.getValue(ProfileModelClass::class.java)
                        list.add(data as ProfileModelClass)

                    }

                    var MyAdapter = HomeAdapter(this@HomeFragment,list)
                    var manager = LinearLayoutManager(
                        context, LinearLayoutManager.HORIZONTAL, false
                    )

//                    var manager=GridLayoutManager(this,LinearLayoutManager)
                    Binding.rcvhome.adapter = MyAdapter
                    Binding.rcvhome.layoutManager = manager
                }

                override fun onCancelled(error: DatabaseError) {
                    TODO("Not yet implemented")
                }

            })


        }


        return Binding.root
    }





}