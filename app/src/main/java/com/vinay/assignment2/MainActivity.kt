package com.vinay.assignment2

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.vinay.assignment2.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    // Step 1: Declare binding variable
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        enableEdgeToEdge()

        // Step 2: Inflate layout using binding
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Step 3: Handle window insets
        ViewCompat.setOnApplyWindowInsetsListener(binding.main) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // Step 4: Use binding to access views
        binding.btnSignup.setOnClickListener {
            startActivity(Intent(this, SignupActivity::class.java))
        }

        binding.btnLogin.setOnClickListener {
            startActivity(Intent(this, LoginActivity::class.java))
        }

        binding.btnSignup2.setOnClickListener {
            startActivity(Intent(this, Signup2Activity::class.java))
        }

        binding.btnLogin2.setOnClickListener {
            startActivity(Intent(this, Login2Activity::class.java))
        }

        binding.btnFacebookLogin.setOnClickListener {
            val intent = Intent(this, FacebookLogin::class.java)
            startActivity(intent)
        }

        binding.btnUpdates.setOnClickListener {
            val githubUrl = "https://github.com/Vinaypartap-Singh/Assignment2AndroidCourse"
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse(githubUrl))
            startActivity(intent)
        }
    }
}
