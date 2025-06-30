package com.vinay.assignment2

import android.os.Bundle
import android.util.Patterns
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.firestore.FirebaseFirestore

class Signup2Activity : AppCompatActivity() {

    private lateinit var db: FirebaseFirestore

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signup2)

        db = FirebaseFirestore.getInstance()

        val email = findViewById<EditText>(R.id.etEmail)
        val password = findViewById<EditText>(R.id.etPassword)
        val confirmPassword = findViewById<EditText>(R.id.etConfirmPassword)
        val signupBtn = findViewById<Button>(R.id.btnSignup2)
        val backBtn = findViewById<Button>(R.id.btnBack)

        signupBtn.setOnClickListener {
            val emailText = email.text.toString().trim()
            val passwordText = password.text.toString().trim()
            val confirmText = confirmPassword.text.toString().trim()

            // 🔍 Email & password validation
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

        backBtn.setOnClickListener {
            finish()
        }
    }
}
