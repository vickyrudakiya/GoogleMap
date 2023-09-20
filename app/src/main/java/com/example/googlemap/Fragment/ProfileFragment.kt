package com.example.googlemap.Fragment

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.googlemap.BeachActivity
import com.example.googlemap.ForestActivity
import com.example.googlemap.LakeActivity
import com.example.googlemap.MountianActivity
import com.example.googlemap.MountianActivity2
import com.example.googlemap.ProfileActivity
import com.example.googlemap.R
import com.example.googlemap.databinding.FragmentProfileBinding


class ProfileFragment : Fragment() {

    lateinit var Binding : FragmentProfileBinding



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        Binding = FragmentProfileBinding.inflate(inflater,container,false)



        initview()

        return Binding.root

    }

    private fun initview() {
        Binding.addTextPfofile.setOnClickListener {

            var i = Intent(requireContext(),ProfileActivity::class.java)
            startActivity(i)
        }

        Binding.addTextMountain.setOnClickListener {

            var i = Intent(requireContext(),MountianActivity2::class.java)
            startActivity(i)
        }
        Binding.addTextBeach.setOnClickListener {

            var i = Intent(requireContext(),BeachActivity::class.java)
            startActivity(i)
        }
        Binding.addTextLake.setOnClickListener {

            var i = Intent(requireContext(),LakeActivity::class.java)
            startActivity(i)
        }
        Binding.addTextForest.setOnClickListener {

            var i = Intent(requireContext(),ForestActivity::class.java)
            startActivity(i)
        }


    }

}


