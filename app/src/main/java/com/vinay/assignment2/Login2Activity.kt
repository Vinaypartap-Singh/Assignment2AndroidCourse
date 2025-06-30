package com.vinay.assignment2

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.firestore.FirebaseFirestore
import com.vinay.assignment2.databinding.ActivityLogin2Binding

class Login2Activity : AppCompatActivity() {

    private lateinit var db: FirebaseFirestore
    private lateinit var binding: ActivityLogin2Binding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Step 1: Inflate the binding layout
        binding = ActivityLogin2Binding.inflate(layoutInflater)
        setContentView(binding.root)

        // Step 2: Initialize Firestore
        db = FirebaseFirestore.getInstance()

        // Step 3: Handle login button click
        binding.btnLogin2.setOnClickListener {
            val emailText = binding.etEmail.text.toString().trim()
            val passwordText = binding.etPassword.text.toString().trim()

            if (emailText.isEmpty() || passwordText.isEmpty()) {
                Toast.makeText(this, "Please fill all fields", Toast.LENGTH_SHORT).show()
            } else {
                db.collection("users2")
                    .whereEqualTo("email", emailText)
                    .whereEqualTo("password", passwordText)
                    .get()
                    .addOnSuccessListener { documents ->
                        if (!documents.isEmpty) {
                            Toast.makeText(this, "Login Successful", Toast.LENGTH_SHORT).show()
                            startActivity(Intent(this, WelcomeActivity::class.java))
                        } else {
                            Toast.makeText(this, "Username or password is incorrect. Try again!", Toast.LENGTH_SHORT).show()
                        }
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
