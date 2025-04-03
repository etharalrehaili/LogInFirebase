package com.example.loginfirebase

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import com.example.loginfirebase.databinding.ActivityHelloBinding
import com.google.firebase.auth.FirebaseAuth

class HelloActivity : ComponentActivity() {

    private lateinit var binding: ActivityHelloBinding
    private lateinit var firebaseAuth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Initialize the binding
        binding = ActivityHelloBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Initialize FirebaseAuth
        firebaseAuth = FirebaseAuth.getInstance()

        // Set up the sign in button
        binding.signinbtn.setOnClickListener {
            // Redirect the user to the SignInActivity
            val intent = Intent(this, SignInActivity::class.java)
            startActivity(intent)
        } // end setOnClickListener

        // Set up the sign Up button
        binding.signupbtn.setOnClickListener {
            // Redirect the user to the SignUpActivity
            val intent = Intent(this, SignUpActivity::class.java)
            startActivity(intent)
        } // end setOnClickListener
    } // end onCreate
}