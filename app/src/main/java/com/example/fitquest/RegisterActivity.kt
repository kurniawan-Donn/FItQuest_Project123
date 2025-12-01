package com.example.fitquest

import android.content.Intent
import android.os.Bundle
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.button.MaterialButton
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout

class RegisterActivity : AppCompatActivity() {

    private lateinit var tilFullName: TextInputLayout
    private lateinit var tilEmail: TextInputLayout
    private lateinit var tilPassword: TextInputLayout
    private lateinit var tilConfirmPassword: TextInputLayout
    private lateinit var etFullName: TextInputEditText
    private lateinit var etEmail: TextInputEditText
    private lateinit var etPassword: TextInputEditText
    private lateinit var etConfirmPassword: TextInputEditText
    private lateinit var btnRegister: MaterialButton
    private lateinit var tvLoginLink: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        // Initialize views
        initializeViews()

        // Setup click listeners
        setupListeners()

        // Handle back button with modern API
        onBackPressedDispatcher.addCallback(this, object : androidx.activity.OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                // Go back to LoginActivity
                finish()
            }
        })
    }

    private fun initializeViews() {
        tilFullName = findViewById(R.id.tilFullName)
        tilEmail = findViewById(R.id.tilEmail)
        tilPassword = findViewById(R.id.tilPassword)
        tilConfirmPassword = findViewById(R.id.tilConfirmPassword)
        etFullName = findViewById(R.id.etFullName)
        etEmail = findViewById(R.id.etEmail)
        etPassword = findViewById(R.id.etPassword)
        etConfirmPassword = findViewById(R.id.etConfirmPassword)
        btnRegister = findViewById(R.id.btnRegister)
        tvLoginLink = findViewById(R.id.tvLoginLink)
    }

    private fun setupListeners() {
        // Register button click
        btnRegister.setOnClickListener {
            handleRegister()
        }

        // Login link click
        tvLoginLink.setOnClickListener {
            finish() // Go back to LoginActivity
        }
    }

    private fun handleRegister() {
        // Get input values
        val fullName = etFullName.text.toString().trim()
        val email = etEmail.text.toString().trim()
        val password = etPassword.text.toString().trim()
        val confirmPassword = etConfirmPassword.text.toString().trim()

        // Reset errors
        tilFullName.error = null
        tilEmail.error = null
        tilPassword.error = null
        tilConfirmPassword.error = null

        // Validate inputs
        var isValid = true

        if (fullName.isEmpty()) {
            tilFullName.error = "Nama lengkap tidak boleh kosong"
            isValid = false
        } else if (fullName.length < 3) {
            tilFullName.error = "Nama minimal 3 karakter"
            isValid = false
        }

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

        if (confirmPassword.isEmpty()) {
            tilConfirmPassword.error = "Konfirmasi password tidak boleh kosong"
            isValid = false
        } else if (password != confirmPassword) {
            tilConfirmPassword.error = "Password tidak cocok"
            isValid = false
        }

        // If validation passed
        if (isValid) {
            // TODO: Implement actual registration logic with backend/database
            // For now, we'll simulate successful registration
            if (registerUser(fullName, email, password)) {
                Toast.makeText(this, "Registrasi berhasil! Silakan login", Toast.LENGTH_SHORT).show()

                // Go back to LoginActivity
                finish()
            } else {
                Toast.makeText(this, "Registrasi gagal. Email mungkin sudah terdaftar", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun registerUser(fullName: String, email: String, password: String): Boolean {
        // TODO: Replace with actual registration logic
        // This should connect to your backend/database

        // For testing purposes, always return true
        // In production, this should:
        // 1. Check if email already exists
        // 2. Hash the password
        // 3. Save to database
        // 4. Return success/failure

        return true
    }
}