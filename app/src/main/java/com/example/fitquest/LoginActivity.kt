package com.example.fitquest

import android.content.Intent
import android.os.Bundle
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.button.MaterialButton
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout

class LoginActivity : AppCompatActivity() {

    private lateinit var tilEmail: TextInputLayout
    private lateinit var tilPassword: TextInputLayout
    private lateinit var etEmail: TextInputEditText
    private lateinit var etPassword: TextInputEditText
    private lateinit var btnLogin: MaterialButton
    private lateinit var tvRegisterLink: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        // Initialize views
        initializeViews()

        // Setup click listeners
        setupListeners()

        // Handle back button with modern API
        onBackPressedDispatcher.addCallback(this, object : androidx.activity.OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                // Prevent going back from login screen
                finishAffinity()
            }
        })
    }

    private fun initializeViews() {
        tilEmail = findViewById(R.id.tilEmail)
        tilPassword = findViewById(R.id.tilPassword)
        etEmail = findViewById(R.id.etEmail)
        etPassword = findViewById(R.id.etPassword)
        btnLogin = findViewById(R.id.btnLogin)
        tvRegisterLink = findViewById(R.id.tvRegisterLink)
    }

    private fun setupListeners() {
        // Login button click
        btnLogin.setOnClickListener {
            handleLogin()
        }

        // Register link click
        tvRegisterLink.setOnClickListener {
            val intent = Intent(this, RegisterActivity::class.java)
            startActivity(intent)
        }
    }

    private fun handleLogin() {
        // Get input values
        val email = etEmail.text.toString().trim()
        val password = etPassword.text.toString().trim()

        // Reset errors
        tilEmail.error = null
        tilPassword.error = null

        // Validate inputs
        var isValid = true

        if (email.isEmpty()) {
            tilEmail.error = "Email tidak boleh kosong"
            isValid = false
        } else if (!android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            tilEmail.error = "Format email tidak valid"
            isValid = false
        }

        if (password.isEmpty()) {
            tilPassword.error = "Password tidak boleh kosong"
            isValid = false
        } else if (password.length < 6) {
            tilPassword.error = "Password minimal 6 karakter"
            isValid = false
        }

        // If validation passed
        if (isValid) {
            // TODO: Implement actual login logic with backend/database
            // For now, we'll use dummy validation
            if (validateLogin(email, password)) {
                Toast.makeText(this, "Login berhasil!", Toast.LENGTH_SHORT).show()

                // Navigate to MainActivity
                val intent = Intent(this, MainActivity::class.java)
                intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                startActivity(intent)
                finish()
            } else {
                Toast.makeText(this, "Email atau password salah", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun validateLogin(email: String, password: String): Boolean {
        // TODO: Replace with actual authentication logic
        // This is just dummy validation for testing

        // For testing purposes, accept any email with password "123456"
        // In production, this should connect to your backend/database

        return password.length >= 6
    }
}