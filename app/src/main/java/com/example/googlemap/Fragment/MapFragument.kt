package com.example.googlemap.Fragment

import android.location.Address
import android.location.Geocoder
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import com.example.googlemap.R
import com.example.googlemap.databinding.FragmentMapBinding
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.Marker
import com.google.android.gms.maps.model.MarkerOptions
import java.io.IOException


class MapFragument : Fragment(), OnMapReadyCallback {

    private lateinit var _binding: FragmentMapBinding
    lateinit var mMap: GoogleMap
    lateinit var idSearchView: SearchView
    var addedMarker: Marker? = null
    val binding get() = _binding!!


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentMapBinding.inflate(inflater, container, false)


        val View = binding.root

        initview()

        return View
    }

    private fun initview() {

//        searchView = findViewById(R.id.idSearchView)

//      val mapFragment = SupportMapFragment().findFragmentById(R.id.map) as SupportMapFragment?
//        val mapFragment = supportFragmentManager.findFragmentById(R.id.map) as SupportMapFragment?
//        val mapFragment: supportFragmentManager.beginTransaction().replace(R.id.map, MapsFragment()).commit()
        val supportMapFragment =
            childFragmentManager.findFragmentById(com.example.googlemap.R.id.map) as SupportMapFragment?
        _binding.idSearchView.setOnQueryTextListener(/* listener = */ object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String): Boolean {
                val location = idSearchView.query.toString()

                var addressList: List<Address>? = null

                if (location != null || location == "") {
                    val geocoder = context?.let { Geocoder(it) }
                    try {
                        if (geocoder != null) {
                            addressList = geocoder.getFromLocationName(location, 1)
                        }
                    } catch (e: IOException) {
                        e.printStackTrace()
                    }
                    val address = addressList!![0]

                    val latLng = LatLng(address.latitude, address.longitude)

                    Log.e(
                        "TAG",
                        "latitude:-  " + address.latitude + " " + "longitude:- " + address.longitude
                    )


                    addedMarker = mMap.addMarker(MarkerOptions().position(latLng).title(location))!!

                    mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(latLng, 13f))


                }

                return false

            }


            override fun onQueryTextChange(newText: String): Boolean {
                addedMarker?.remove()
                return false

            }

        })

        supportMapFragment!!.getMapAsync(this)

    }

    override fun onMapReady(p0: GoogleMap) {
        mMap = p0

    }

}




