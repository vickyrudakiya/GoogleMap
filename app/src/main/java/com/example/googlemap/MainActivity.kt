package com.example.googlemap

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import com.example.googlemap.databinding.ActivityMainBinding
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.android.gms.tasks.Task
import com.google.firebase.FirebaseApp
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class MainActivity : AppCompatActivity() {
    lateinit var Binding: ActivityMainBinding
    lateinit var auth: FirebaseAuth
    lateinit var mGoogleSignInClient: GoogleSignInClient
    val Req_Code: Int = 123
    lateinit var firebaseAuth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        Binding = ActivityMainBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        FirebaseApp.initializeApp(this)
        setContentView(Binding.root)

        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken(getString(R.string.default_web_client_id))
            .requestEmail()
            .build()

        mGoogleSignInClient = GoogleSignIn.getClient(this, gso)
        firebaseAuth = FirebaseAuth.getInstance()

        Binding.imggoogle.setOnClickListener { view: View? ->
            Toast.makeText(this, "Logging In", Toast.LENGTH_SHORT).show()
            signInGoogle()
        }
    }

    private fun signInGoogle() {
        val signInIntent: Intent = mGoogleSignInClient.signInIntent
        startActivityForResult(signInIntent, Req_Code)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == Req_Code) {
            val task: Task<GoogleSignInAccount> = GoogleSignIn.getSignedInAccountFromIntent(data)
            handleResult(task)
        }
    }

    private fun handleResult(completedTask: Task<GoogleSignInAccount>) {
        try {
            val account: GoogleSignInAccount? = completedTask.getResult(ApiException::class.java)
            if (account != null) {
                UpdateUI(account)
            }
        } catch (e: ApiException) {
            Toast.makeText(this, e.toString(), Toast.LENGTH_SHORT).show()
        }
    }

    private fun UpdateUI(account: GoogleSignInAccount) {
        val credential = GoogleAuthProvider.getCredential(account.idToken, null)
        firebaseAuth.signInWithCredential(credential).addOnCompleteListener { task ->
            if (task.isSuccessful) {

                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
                finish()
            }
        }
    }

    override fun onStart() {
        super.onStart()
        if (GoogleSignIn.getLastSignedInAccount(this) != null) {
            startActivity(
                Intent(
                    this, MainActivity
                    ::class.java
                )
            )
            finish()
        }
        initview()
    }


    fun initview() {

        auth = Firebase.auth

        Binding.BtnLogin.setOnClickListener {

            var email = Binding.Email.text.toString()
            var password = Binding.Password.text.toString()

            auth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this) { task ->
                    if (task.isSuccessful) {

                        var i = Intent(this,BottamActivity::class.java)
                        startActivity(i)

                    } else {

                        Toast.makeText(this, "Please Sign up ", Toast.LENGTH_SHORT).show()
                    }
                }


        }

//         Binding.BtnLogin.setOnClickListener {
//
//             var email = Binding.Email.text.toString()
//             var password = Binding.Password.text.toString()
//
//             auth.signInWithEmailAndPassword(email, password)
//                 .addOnCompleteListener(this) { task ->
//                     if (task.isSuccessful) {
//
//                         var i = Intent(this, ConectingActivity::class.java)
//                         startActivity(i)
//
//                         Toast.makeText(this, "Login Succesfull", Toast.LENGTH_SHORT).show()
//
//                     } else {
//
//                         Toast.makeText(this, "Please Enter 6 Digits", Toast.LENGTH_SHORT).show()
//                     }
//                 }
//
//
//         }


        Binding.Signup.setOnClickListener {

            var i = Intent(this,CreateAccount::class.java)
            startActivity(i)
        }
    }
}




