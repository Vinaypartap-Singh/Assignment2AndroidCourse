package com.vinay.assignment2

import android.os.Bundle
import android.util.Patterns
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.firestore.FirebaseFirestore
import com.vinay.assignment2.databinding.ActivitySignup2Binding

class Signup2Activity : AppCompatActivity() {

    private lateinit var db: FirebaseFirestore
    private lateinit var binding: ActivitySignup2Binding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Step 1: Inflate binding
        binding = ActivitySignup2Binding.inflate(layoutInflater)
        setContentView(binding.root)

        // Step 2: Initialize Firestore
        db = FirebaseFirestore.getInstance()

        // Step 3: Handle signup
        binding.btnSignup2.setOnClickListener {
            val emailText = binding.etEmail.text.toString().trim()
            val passwordText = binding.etPassword.text.toString().trim()
            val confirmText = binding.etConfirmPassword.text.toString().trim()

            // Validate password format
            val passwordPattern = Regex("^(?=.*[A-Z])(?=.*[@#\$%^&+=!]).{8,15}$")

            if (!Patterns.EMAIL_ADDRESS.matcher(emailText).matches()) {
                Toast.makeText(this, "Invalid email format", Toast.LENGTH_SHORT).show()
            } else if (!passwordPattern.matches(passwordText)) {
                Toast.makeText(this, "Password must be 8–15 chars, include 1 uppercase & 1 special char", Toast.LENGTH_LONG).show()
            } else if (passwordText != confirmText) {
                Toast.makeText(this, "Passwords do not match", Toast.LENGTH_SHORT).show()
            } else {
                val user = hashMapOf(
                    "email" to emailText,
                    "password" to passwordText
                )

                db.collection("users2").add(user)
                    .addOnSuccessListener {
                        Toast.makeText(this, "Signup2 successful", Toast.LENGTH_SHORT).show()
                        finish()
                    }
                    .addOnFailureListener {
                        Toast.makeText(this, "Error: ${it.message}", Toast.LENGTH_SHORT).show()
                    }
            }
        }

        // Step 4: Handle back button
        binding.btnBack.setOnClickListener {
            finish()
        }
    }
}
