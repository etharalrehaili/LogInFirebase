package com.example.loginfirebase

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import com.example.loginfirebase.databinding.ActivityMainBinding
import com.google.firebase.auth.FirebaseAuth

class MainActivity : ComponentActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var firebaseAuth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Initialize the binding
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Initialize FirebaseAuth
        firebaseAuth = FirebaseAuth.getInstance()

        // Set up the sign out button
        binding.SignOut.setOnClickListener {

            // Sign out the current user
            firebaseAuth.signOut()

            // Show a Toast to indicate successful sign-out
            Toast.makeText(this, "Signed out successfully", Toast.LENGTH_SHORT).show()

            // Redirect the user to the SignInActivity
            val intent = Intent(this, SignInActivity::class.java)
            startActivity(intent)

            finish()
        } // end setOnClickListener
    } // end onCreate
} // end MainActivity




















