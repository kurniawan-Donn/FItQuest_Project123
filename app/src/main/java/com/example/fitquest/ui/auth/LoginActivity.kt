package com.example.fitquest.ui.auth

import android.content.Intent
import android.os.Bundle
import android.util.Patterns
import android.view.View
import android.widget.ScrollView
import android.widget.TextView
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentContainerView
import com.example.fitquest.R
import com.example.fitquest.ui.MainActivity
import com.google.android.material.button.MaterialButton
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout
import androidx.core.view.isVisible

class LoginActivity : AppCompatActivity() {

    private lateinit var tilEmail: TextInputLayout
    private lateinit var tilPassword: TextInputLayout
    private lateinit var etEmail: TextInputEditText
    private lateinit var etPassword: TextInputEditText
    private lateinit var btnLogin: MaterialButton
    private lateinit var tvRegisterLink: TextView
    private lateinit var fragmentContainer: FragmentContainerView
    private lateinit var loginScrollView: ScrollView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        inisialisasiView()
        aturPendengar()
        aturHandlerTombolKembali()
    }

    private fun inisialisasiView() {
        tilEmail = findViewById(R.id.tilEmail)
        tilPassword = findViewById(R.id.tilPassword)
        etEmail = findViewById(R.id.etEmail)
        etPassword = findViewById(R.id.etPassword)
        btnLogin = findViewById(R.id.btnLogin)
        tvRegisterLink = findViewById(R.id.tvRegisterLink)
        fragmentContainer = findViewById(R.id.fragment_container)
        loginScrollView = findViewById(R.id.login_scrollview)
    }

    private fun aturPendengar() {
        btnLogin.setOnClickListener {
            prosesLogin()
        }

        tvRegisterLink.setOnClickListener {
            bukaFragmentDaftar()
        }
    }

    private fun aturHandlerTombolKembali() {
        onBackPressedDispatcher.addCallback(this, object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                if (fragmentContainer.isVisible) {
                    tampilkanFormLogin() // Kembali ke form login
                } else {
                    finishAffinity()
                }
            }
        })
    }

    // Fungsi public untuk menampilkan form login (bisa dipanggil dari fragment)
    fun tampilkanFormLogin() {
        fragmentContainer.visibility = View.GONE
        loginScrollView.visibility = View.VISIBLE

        // Hapus fragment dari back stack jika ada
        if (supportFragmentManager.backStackEntryCount > 0) {
            supportFragmentManager.popBackStack()
        }
    }

    private fun bukaFragmentDaftar() {
        loginScrollView.visibility = View.GONE
        fragmentContainer.visibility = View.VISIBLE

        val fragmentDaftar = RegisterFragment()
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_container, fragmentDaftar)
            .addToBackStack("daftar")
            .commit()
    }

    private fun prosesLogin() {
        val email = etEmail.text.toString().trim()
        val password = etPassword.text.toString().trim()

        tilEmail.error = null
        tilPassword.error = null

        var valid = true

        if (email.isEmpty()) {
            tilEmail.error = "Email tidak boleh kosong"
            valid = false
        } else if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            tilEmail.error = "Format email tidak valid"
            valid = false
        }

        if (password.isEmpty()) {
            tilPassword.error = "Password tidak boleh kosong"
            valid = false
        } else if (password.length < 6) {
            tilPassword.error = "Password minimal 6 karakter"
            valid = false
        }

        if (valid) {
            if (validasiLogin(email, password)) {
                Toast.makeText(this, "Login berhasil!", Toast.LENGTH_SHORT).show()
                val intent = Intent(this, MainActivity::class.java)
                intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                startActivity(intent)
                finish()
            } else {
                Toast.makeText(this, "Email atau password salah", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun validasiLogin(email: String, password: String): Boolean {
        // TODO: Implementasi validasi dengan database/API
        // Untuk sekarang, return true jika password minimal 6 karakter
        return password.length >= 6
    }
}