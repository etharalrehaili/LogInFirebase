package com.example.loginfirebase

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import com.example.loginfirebase.databinding.ActivitySignUpBinding
import com.google.firebase.auth.FirebaseAuth
import java.util.regex.Pattern

class SignUpActivity : ComponentActivity() {

    private lateinit var binding: ActivitySignUpBinding
    private lateinit var firebaseAuth: FirebaseAuth

    // Regular expression for validating an email format
    private val emailPattern: Pattern = Pattern.compile(
        "^[a-zA-Z0-9._-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,4}$"
    )

    // Regular expression for validating password format (at least 8 characters, one capital, one small, one number, one symbol)
    private val passwordPattern: Pattern = Pattern.compile(
        "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,}$"
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivitySignUpBinding.inflate(layoutInflater)
        setContentView(binding.root)

        firebaseAuth = FirebaseAuth.getInstance()

        binding.alreadyhaveaccount.setOnClickListener {
            val intent = Intent(this, SignInActivity::class.java)
            startActivity(intent)
        }

        binding.btnSignUp.setOnClickListener {
            var email = binding.inputemail.text.toString()
            var pass = binding.password.text.toString()
            val confirmPass = binding.confirmpassword.text.toString()

            // Check if the fields are not empty
            if (email.isEmpty() || pass.isEmpty() || confirmPass.isEmpty()) {
                Toast.makeText(this, "Empty Fields Are not Allowed", Toast.LENGTH_SHORT).show()
            } else if (!isValidEmail(email)) {
                // If the email format is invalid, show a Toast message
                Toast.makeText(this, "Please, enter a valid email", Toast.LENGTH_SHORT).show()
            } else if (pass != confirmPass) {
                Toast.makeText(this, "Password is not matching", Toast.LENGTH_SHORT).show()
            } else if (!isValidPassword(pass)) {
                // If the password doesn't meet the criteria, show a Toast message
                Toast.makeText(
                    this,
                    "Password must be at least 8 characters, include one uppercase letter, one lowercase letter, one number, and one symbol",
                    Toast.LENGTH_LONG
                ).show()
            } else {
                // Create a new user with Firebase Authentication
                firebaseAuth.createUserWithEmailAndPassword(email, pass).addOnCompleteListener {
                    if (it.isSuccessful) {
                        // Show a Toast to indicate successful creation
                        Toast.makeText(this, "Account Created Successfully!", Toast.LENGTH_SHORT)
                            .show()
                        // Redirect the user to the SignInActivity
                        val intent = Intent(this, SignInActivity::class.java)
                        startActivity(intent)
                    } else {
                        Toast.makeText(this, it.exception.toString(), Toast.LENGTH_SHORT).show()
                    }
                }
            }
        } // end setOnClickListener
    } // end onCreate

    // Function to validate if the email matches the regex pattern
    private fun isValidEmail(email: String): Boolean {
        return emailPattern.matcher(email).matches()
    }

    // Function to validate the password with regex (check if password meets the requirements)
    private fun isValidPassword(password: String): Boolean {
        return passwordPattern.matcher(password).matches()
    }
} // end SignUpActivity
