package com.example.googlemap

import android.app.ProgressDialog
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.example.googlemap.ModelClass.MountianModelClass
import com.example.googlemap.ModelClass.ProfileModelClass
import com.example.googlemap.databinding.ActivityMountian2Binding
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference
import java.util.UUID

class MountianActivity2 : AppCompatActivity() {
    lateinit var Binding: ActivityMountian2Binding
    private val PICK_IMAGE_REQUEST = 100
    lateinit var uri: Uri
    lateinit var ImageUri: Uri
    var reference = FirebaseDatabase.getInstance().reference


    override fun onCreate(savedInstanceState: Bundle?) {
        Binding = ActivityMountian2Binding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(Binding.root)

        initview()
    }

    private fun initview() {

        Binding.BtnSubmitMountain.setOnClickListener {
            var place = Binding.edtMountainPlace.text.toString()
            var price = Binding.edtMountainPrice.text.toString()
            var day = Binding.edtMountainDay.text.toString()
            var people = Binding.edtMountainPeople.text.toString()
            var key = reference.root.child("MountainTb").push().key ?: ""

//             var modelClass = ProfileModelClass(city,place,price,day,rating,email,phone,description,key,ImageUri)
            var modelClass = MountianModelClass(place, price, day, people, key, ImageUri)

            reference.root.child("MountainTb").child(key).setValue(modelClass).addOnCompleteListener {

                if (it.isSuccessful) {
                    Toast.makeText(this, "Data Added Successful", Toast.LENGTH_SHORT).show()
                }
            }.addOnCanceledListener { Log.e("TAG", "Error: " + it) }
        }


//        var reference = FirebaseDatabase.getInstance().reference

        Binding.ButtonAddImageMountain.setOnClickListener {

            val intent = Intent()
            intent.type = "image/*"
            intent.action = Intent.ACTION_GET_CONTENT
            startActivityForResult(
                Intent.createChooser(
                    intent,
                    "Select Image from here..."
                ),
                PICK_IMAGE_REQUEST
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
                                this@MountianActivity2,
                                "Image Uploaded!!",
                                Toast.LENGTH_SHORT
                            )
                            .show()
                    }
                    .addOnFailureListener { e -> // Error, Image not uploaded
                        Toast
                            .makeText(
                                this@MountianActivity2,
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