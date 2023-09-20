package com.example.googlemap.Fragment

import android.app.ProgressDialog
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.googlemap.Adapter.HomeAdapter
import com.example.googlemap.BeachActivity
import com.example.googlemap.BeachRCVActivity
import com.example.googlemap.ForestRCVActivity
import com.example.googlemap.LakeActivity
import com.example.googlemap.LakeRCVActivity
import com.example.googlemap.ModelClass.ProfileModelClass
import com.example.googlemap.MountianActivity
import com.example.googlemap.databinding.FragmentHomeBinding
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.core.Context

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER

class HomeFragment : Fragment() {
    private var Binding: FragmentHomeBinding? = null
    private val binding get() = Binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {


        Binding = FragmentHomeBinding.inflate(inflater, container, false)
        var View = binding.root
        var reference = FirebaseDatabase.getInstance().reference
        var list: ArrayList<ProfileModelClass> = ArrayList()

//        val progressDialog = ProgressDialog(context)
//        progressDialog.setTitle("Please Wait")
//        progressDialog.setMessage("Application is loading, please wait")
//        progressDialog.show()
        reference.root.child("AdminTb").addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                for (child in snapshot.children) {
                    var data: ProfileModelClass? = child.getValue(ProfileModelClass::class.java)
//                    list.add(data as ProfileModelClass)

                    if (data != null) {
                        list.add(data)
                    }
                }
//                progressDialog.dismiss()
                var MyAdapter = HomeAdapter(this@HomeFragment, list)
                var manager = LinearLayoutManager(
                    context, LinearLayoutManager.HORIZONTAL, false)
                binding.rcv.adapter = MyAdapter
                binding.rcv.layoutManager = manager
            }
            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }
        })


        initview()

        return View


    }

    private fun initview() {

//        Binding?.linMountain?.setOnClickListener {
//
//            Toast.makeText(requireActivity(), "Sucess", Toast.LENGTH_SHORT).show()
//        }

//        Binding?.linMountain?.setOnClickListener {
//
//            var i = Intent(requireActivity().application, MountianActivity::class.java)
//            startActivity(i)
//
//            startActivity(Intent(requireContext(),MountianActivity::class.java))
//            Toast.makeText(requireContext(), "success", Toast.LENGTH_SHORT).show()
//            Log.e("TAG", "Success: " )
//        }

        binding.linMountain.setOnClickListener {
            var i = Intent(requireActivity().application,MountianActivity::class.java)
            startActivity(i)
        }
        binding.linBeach.setOnClickListener {
            var i = Intent(requireActivity().application,BeachRCVActivity::class.java)
            startActivity(i)
        }
        binding.linLake.setOnClickListener {
            var i = Intent(requireActivity().application,LakeRCVActivity::class.java)
            startActivity(i)
        }
        binding.linForest.setOnClickListener {
            var i = Intent(requireActivity().application,ForestRCVActivity::class.java)
            startActivity(i)
        }



//        Binding?.linBeach?.setOnClickListener {
//
//            var i = Intent(context, BeachActivity::class.java)
//            startActivity(i)
//        }
//        Binding?.linLake?.setOnClickListener {
//
//            var i = Intent(context, LakeActivity::class.java)
//            startActivity(i)
//        }


    }


}