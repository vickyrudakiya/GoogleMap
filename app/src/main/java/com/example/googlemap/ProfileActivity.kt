package com.example.googlemap

import android.app.ProgressDialog
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.googlemap.Adapter.HomeAdapter
import com.example.googlemap.ModelClass.ProfileModelClass
import com.example.googlemap.databinding.ActivityProfileBinding
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference
import java.util.UUID




class ProfileActivity : AppCompatActivity() {
    lateinit var Binding : ActivityProfileBinding
    var reference = FirebaseDatabase.getInstance().reference
    private val PICK_IMAGE_REQUEST = 100
    lateinit var uri: Uri
    lateinit var ImageUri: Uri


    override fun onCreate(savedInstanceState: Bundle?) {
        Binding= ActivityProfileBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(Binding.root)

        initview()


    }

    private fun initview() {

        Binding.BtnSubmit.setOnClickListener {
            var place = Binding.edtPlace.text.toString()
            var city = Binding.edtcity.text.toString()
            var price = Binding.edtprice.text.toString()
            var day = Binding.edtDay.text.toString()
            var rating = Binding.edtRating.text.toString()
            var email = Binding.edtEmail.text.toString()
            var phone = Binding.edtphone.text.toString()
            var description = Binding.edtDescription.text.toString()

            var key = reference.root.child("AdminTb").push().key ?:""

            var modelClass = ProfileModelClass(place,email,phone,description,city,price,rating,day,key,ImageUri)

            reference.root.child("AdminTb").child(key).setValue(modelClass).addOnCompleteListener {

                if(it.isSuccessful)
                {
                    Toast.makeText(this, "Data Added Successful", Toast.LENGTH_SHORT).show()
                }
            }.addOnCanceledListener { Log.e("TAG", "Error: "+ it ) }
        }






        var reference = FirebaseDatabase.getInstance().reference

            Binding.ButtonAddImage.setOnClickListener {

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
                                this@ProfileActivity,
                                "Image Uploaded!!",
                                Toast.LENGTH_SHORT
                            )
                            .show()
                    }
                    .addOnFailureListener { e -> // Error, Image not uploaded
                        progressDialog.dismiss()
                        Toast
                            .makeText(
                                this@ProfileActivity,
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

            // Get the Uri of data
            uri = data.data!!
            uploadImage()

        }
    }





}