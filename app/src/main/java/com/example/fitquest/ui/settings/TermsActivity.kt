package com.example.fitquest.ui.settings

import android.content.Context
import android.os.Bundle
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import com.example.fitquest.R
import com.example.fitquest.ui.utils.LocaleHelper
import com.google.android.material.button.MaterialButton

class TermsActivity : AppCompatActivity() {

    private lateinit var btnBack: ImageView
    private lateinit var btnKembali: MaterialButton

    override fun attachBaseContext(newBase: Context) {
        super.attachBaseContext(LocaleHelper.onAttach(newBase))
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_terms)

        initializeViews()
        setupListeners()
    }

    private fun initializeViews() {
        btnBack = findViewById(R.id.btnBack)
        btnKembali = findViewById(R.id.btnKembali)
    }

    private fun setupListeners() {
        btnBack.setOnClickListener {
            finish()
        }

        btnKembali.setOnClickListener {
            finish()
        }
    }
}