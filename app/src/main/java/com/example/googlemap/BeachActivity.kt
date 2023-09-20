package com.example.googlemap

import android.app.ProgressDialog
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.example.googlemap.ModelClass.BeachModelClass
import com.example.googlemap.ModelClass.MountianModelClass
import com.example.googlemap.databinding.ActivityBeachBinding
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference
import java.util.UUID

class BeachActivity : AppCompatActivity() {
    lateinit var Binding : ActivityBeachBinding
    private val PICK_IMAGE_REQUEST = 100
    lateinit var uri: Uri
    lateinit var ImageUri: Uri
    var reference = FirebaseDatabase.getInstance().reference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Binding = ActivityBeachBinding.inflate(layoutInflater)
        setContentView(Binding.root)

        initview()
    }

    private fun initview() {

        Binding.BtnSubmitBeach.setOnClickListener {
            var place = Binding.edtBeachPlace.text.toString()
            var price = Binding.edtBeachPrice.text.toString()
            var day = Binding.edtBeachDay.text.toString()
            var people = Binding.edtBeachPeople.text.toString()
            var key = reference.root.child("BeachTb").push().key ?: ""

//             var modelClass = ProfileModelClass(city,place,price,day,rating,email,phone,description,key,ImageUri)
            var modelClass = BeachModelClass(place, price, day, people, key, ImageUri)

            reference.root.child("BeachTb").child(key).setValue(modelClass).addOnCompleteListener {

                if (it.isSuccessful) {
                    Toast.makeText(this, "Data Added Successful", Toast.LENGTH_SHORT).show()
                }
            }.addOnCanceledListener { Log.e("TAG", "Error: " + it) }
        }

        Binding.ButtonAddImageBeach.setOnClickListener {

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
                                this@BeachActivity,
                                "Image Uploaded!!",
                                Toast.LENGTH_SHORT
                            )
                            .show()
                    }
                    .addOnFailureListener { e -> // Error, Image not uploaded
                        Toast
                            .makeText(
                                this@BeachActivity,
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