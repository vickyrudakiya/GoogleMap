package com.example.googlemap.Fragment

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
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

    }

}


