package com.vinay.assignment2

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.firestore.FirebaseFirestore
import com.vinay.assignment2.databinding.ActivitySignupBinding

class SignupActivity : AppCompatActivity() {

    private lateinit var db: FirebaseFirestore
    private lateinit var binding: ActivitySignupBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Step 1: Inflate layout using View Binding
        binding = ActivitySignupBinding.inflate(layoutInflater)
        setContentView(binding.root)
        // Step 2: Initialize Firestore
        db = FirebaseFirestore.getInstance()

        // Step 3: Handle signup
        binding.btnSignup.setOnClickListener {
            val user = binding.etUsername.text.toString().trim()
            val pass = binding.etPassword.text.toString().trim()
            val confirmPass = binding.etConfirmPassword.text.toString().trim()

            if (user.isEmpty() || pass.isEmpty() || confirmPass.isEmpty()) {
                Toast.makeText(this, "Please fill all fields", Toast.LENGTH_SHORT).show()
            } else if (pass != confirmPass) {
                Toast.makeText(this, "Passwords do not match", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(this, "running", Toast.LENGTH_SHORT).show() // This Toast is not showing
                val userData = hashMapOf(
                    "username" to user,
                    "password" to pass
                )

                db.collection("users").add(userData) // The problem is likely related to 'db' here
                    .addOnSuccessListener {
                        Toast.makeText(this, "Signup successful", Toast.LENGTH_SHORT).show()
                        finish()
                    }
                    .addOnFailureListener {
                        Toast.makeText(this, "Signup failed: ${it.message}", Toast.LENGTH_SHORT).show()
                    }
            }
        }

        // Step 4: Handle back button
        binding.btnBack.setOnClickListener {
            finish()
        }
    }
}
