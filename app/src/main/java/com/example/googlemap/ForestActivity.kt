package com.example.googlemap

import android.app.ProgressDialog
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.example.googlemap.Adapter.ForestSecondAdapter
import com.example.googlemap.ModelClass.ForestModelClass
import com.example.googlemap.ModelClass.MountianModelClass
import com.example.googlemap.databinding.ActivityForestBinding
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference
import java.util.UUID

class ForestActivity : AppCompatActivity() {
    lateinit var Binding: ActivityForestBinding
    lateinit var MyAdapter: ForestSecondAdapter
    private val PICK_IMAGE_REQUEST = 100
    lateinit var uri: Uri
    lateinit var ImageUri: Uri
    var reference = FirebaseDatabase.getInstance().reference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Binding = ActivityForestBinding.inflate(layoutInflater)
        setContentView(Binding.root)

        initview()
    }

    private fun initview() {
        Binding.BtnSubmitForest.setOnClickListener {
            var place = Binding.edtForestPlace.text.toString()
            var price = Binding.edtForestPrice.text.toString()
            var day = Binding.edtForestDay.text.toString()
            var people = Binding.edtForestPeople.text.toString()
            var key = reference.root.child("ForestTb").push().key ?: ""

//             var modelClass = ProfileModelClass(city,place,price,day,rating,email,phone,description,key,ImageUri)
            var modelClass = ForestModelClass(place, price, day, people, key, ImageUri)

            reference.root.child("ForestTb").child(key).setValue(modelClass).addOnCompleteListener {

                if (it.isSuccessful) {
                    Toast.makeText(this, "Data Added Successful", Toast.LENGTH_SHORT).show()
                }
            }.addOnCanceledListener { Log.e("TAG", "Error: " + it) }
        }

        Binding.ButtonAddImageForest.setOnClickListener {

            val intent = Intent()
            intent.type = "image/*"
            intent.action = Intent.ACTION_GET_CONTENT
            startActivityForResult(
                Intent.createChooser(
                    intent,
                    "Select Image from here..."
                ), PICK_IMAGE_REQUEST
            )
        }


    }

    private fun uploadImage() {
        if (uri != null) {

            // Code for showing progressDialog while uploading
            val progressDialog = ProgressDialog(this)
            progressDialog.setTitle("Uploading...")
            progressDialog.show()

            // Defining the child of storageReference
            val ref: StorageReference = FirebaseStorage.getInstance().getReference()
                .child("images/" + UUID.randomUUID().toString())

            // adding listeners on upload
            // or failure of image
            if (ref != null) {
                ref.putFile(uri).continueWith {

                    ref.downloadUrl.addOnCompleteListener {
                        ImageUri = it.result
                    }


                }

                    .addOnSuccessListener { // Image uploaded successfully
                        // Dismiss dialog
                        progressDialog.dismiss()
                        Toast
                            .makeText(
                                this@ForestActivity,
                                "Image Uploaded!!",
                                Toast.LENGTH_SHORT
                            )
                            .show()
                    }
                    .addOnFailureListener { e -> // Error, Image not uploaded
                        Toast
                            .makeText(
                                this@ForestActivity,
                                "Failed " + e.message,
                                Toast.LENGTH_SHORT
                            )
                            .show()
                    }

            }
        }

    }

    override fun onActivityResult(
        requestCode: Int,
        resultCode: Int,
        data: Intent?
    ) {
        super.onActivityResult(
            requestCode,
            resultCode,
            data
        )

        if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK && data != null && data.data != null) {
            uri = data.data!!
            uploadImage()

        }
    }

}