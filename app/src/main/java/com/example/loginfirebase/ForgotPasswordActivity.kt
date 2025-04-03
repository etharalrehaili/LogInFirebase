package com.example.loginfirebase

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import com.example.loginfirebase.databinding.ActivityForgotPassBinding
import com.google.firebase.auth.FirebaseAuth

class ForgotPasswordActivity : ComponentActivity() {

    private lateinit var binding: ActivityForgotPassBinding
    private lateinit var firebaseAuth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Initialize the binding
        binding = ActivityForgotPassBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Initialize FirebaseAuth
        firebaseAuth = FirebaseAuth.getInstance()

        // Redirect the user to the SignInActivity
        binding.btnBack.setOnClickListener {
            val intent = Intent(this, SignInActivity::class.java)
            startActivity(intent)
        }

        // Set up the Reset Password Button
        binding.btnResetPass.setOnClickListener {
            val email = binding.etresetemail.text.toString()
            firebaseAuth.sendPasswordResetEmail(email).addOnSuccessListener {
                Toast.makeText(this, "Please Check Your Email", Toast.LENGTH_SHORT).show()
            }.addOnFailureListener {
                Toast.makeText(this, it.toString(), Toast.LENGTH_SHORT).show()
            }
        } // end setOnClickListener
    } // end onCreate

} // end SignInActivity
