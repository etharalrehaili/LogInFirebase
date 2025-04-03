package com.example.loginfirebase

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import com.example.loginfirebase.databinding.ActivitySignInBinding
import com.google.firebase.auth.FirebaseAuth
import java.util.regex.Pattern


class SignInActivity : ComponentActivity() {

    private lateinit var binding: ActivitySignInBinding
    private lateinit var firebaseAuth: FirebaseAuth
    private lateinit var googleSignInActivity: SignInActivity

    // Regular expression for validating an email format
    private val emailPattern: Pattern = Pattern.compile(
        "^[a-zA-Z0-9._-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,4}$"
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Initialize the binding
        binding = ActivitySignInBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Initialize FirebaseAuth
        firebaseAuth = FirebaseAuth.getInstance()

        // Set up the don't have account textView
        binding.donthaveaccount.setOnClickListener {
            // Redirect the user to the SignUpActivity
            val intent = Intent(this, SignUpActivity::class.java)
            startActivity(intent)
        } // end setOnClickListener

        // Set up the Forgot Password textView
        binding.forgotpass.setOnClickListener {
            // Redirect the user to the ForgotPasswordActivity
            val intent = Intent(this, ForgotPasswordActivity::class.java)
            startActivity(intent)
        } // end setOnClickListener

        binding.button.setOnClickListener {
            val email = binding.email.text.toString()
            val pass = binding.password.text.toString()

            // Check if the fields are not empty
            if (email.isEmpty() || pass.isEmpty()) {
                Toast.makeText(this, "Empty Fields Are not Allowed", Toast.LENGTH_SHORT).show()
            } else if (!isValidEmail(email)) {
                // If the email format is invalid, show a Toast message
                Toast.makeText(this, "Please, enter a valid email", Toast.LENGTH_SHORT).show()
            } else {
                // Attempt to sign in if the email format is valid
                firebaseAuth.signInWithEmailAndPassword(email, pass).addOnCompleteListener {
                    if (it.isSuccessful) {
                        // Show a Toast to indicate successful sign-in
                        Toast.makeText(this, "Signed In successfully", Toast.LENGTH_SHORT).show()

                        // Redirect the user to the MainActivity
                        val intent = Intent(this, MainActivity::class.java)
                        startActivity(intent)
                    } else {
                        Toast.makeText(this, "Invalid", Toast.LENGTH_SHORT).show()
                    }
                }
            }
        } // end setOnClickListener
    } // end onCreate

    // Function to validate if the email matches the regex pattern
    private fun isValidEmail(email: String): Boolean {
        return emailPattern.matcher(email).matches()
    }

    override fun onStart() {
        super.onStart()

        if (firebaseAuth.currentUser != null) {
            // If the user is already signed in, navigate to the main activity
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
    } // end onStart()
} // end SignInActivity

























