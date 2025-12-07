package com.example.fitquest.ui.auth

import android.os.Bundle
import android.util.Patterns
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.fitquest.R
import com.google.android.material.button.MaterialButton
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout

class RegisterFragment : Fragment() {

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

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_register, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initializeViews(view)
        setupListeners()
    }

    private fun initializeViews(view: View) {
        tilFullName = view.findViewById(R.id.tilFullName)
        tilEmail = view.findViewById(R.id.tilEmail)
        tilPassword = view.findViewById(R.id.tilPassword)
        tilConfirmPassword = view.findViewById(R.id.tilConfirmPassword)
        etFullName = view.findViewById(R.id.etFullName)
        etEmail = view.findViewById(R.id.etEmail)
        etPassword = view.findViewById(R.id.etPassword)
        etConfirmPassword = view.findViewById(R.id.etConfirmPassword)
        btnRegister = view.findViewById(R.id.btnRegister)
        tvLoginLink = view.findViewById(R.id.tvLoginLink)
    }

    private fun setupListeners() {
        btnRegister.setOnClickListener {
            handleRegister()
        }

        tvLoginLink.setOnClickListener {
            (requireActivity() as? LoginActivity)?.tampilkanFormLogin()
        }
    }

    private fun handleRegister() {
        val fullName = etFullName.text.toString().trim()
        val email = etEmail.text.toString().trim()
        val password = etPassword.text.toString().trim()
        val confirmPassword = etConfirmPassword.text.toString().trim()

        tilFullName.error = null
        tilEmail.error = null
        tilPassword.error = null
        tilConfirmPassword.error = null

        var isValid = true

        if (fullName.isEmpty()) {
            tilFullName.error = getString(R.string.name_empty)
            isValid = false
        } else if (fullName.length < 3) {
            tilFullName.error = getString(R.string.name_min)
            isValid = false
        }

        if (email.isEmpty()) {
            tilEmail.error = getString(R.string.email_empty)
            isValid = false
        } else if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            tilEmail.error = getString(R.string.email_invalid)
            isValid = false
        }

        if (password.isEmpty()) {
            tilPassword.error = getString(R.string.password_empty)
            isValid = false
        } else if (password.length < 6) {
            tilPassword.error = getString(R.string.password_min)
            isValid = false
        }

        if (confirmPassword.isEmpty()) {
            tilConfirmPassword.error = getString(R.string.confirm_password_empty)
            isValid = false
        } else if (password != confirmPassword) {
            tilConfirmPassword.error = getString(R.string.password_not_match)
            isValid = false
        }

        if (isValid) {
            if (registerUser(fullName, email, password)) {
                Toast.makeText(requireContext(), getString(R.string.register_success), Toast.LENGTH_SHORT).show()
                (requireActivity() as? LoginActivity)?.tampilkanFormLogin()
            } else {
                Toast.makeText(requireContext(), getString(R.string.register_failed), Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun registerUser(fullName: String, email: String, password: String): Boolean {
        // TODO: Implementasi registrasi dengan database/API
        return true
    }
}