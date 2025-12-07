package com.example.fitquest.ui.settings

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.ImageButton
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView
import com.example.fitquest.R
import com.example.fitquest.ui.MainActivity
import com.example.fitquest.ui.utils.LocaleHelper

class LanguageActivity : AppCompatActivity() {

    private lateinit var btnBack: ImageButton
    private lateinit var cardIndonesian: CardView
    private lateinit var cardEnglish: CardView
    private lateinit var ivSelectedIndonesian: ImageView
    private lateinit var ivSelectedEnglish: ImageView

    private var selectedLanguage = "in"

    override fun attachBaseContext(newBase: Context) {
        super.attachBaseContext(LocaleHelper.onAttach(newBase))
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_language)

        initializeViews()
        loadCurrentLanguage()
        setupListeners()
        updateSelectionUI()
    }

    private fun initializeViews() {
        btnBack = findViewById(R.id.btnBack)
        cardIndonesian = findViewById(R.id.cardIndonesian)
        cardEnglish = findViewById(R.id.cardEnglish)
        ivSelectedIndonesian = findViewById(R.id.ivSelectedIndonesian)
        ivSelectedEnglish = findViewById(R.id.ivSelectedEnglish)
    }

    private fun loadCurrentLanguage() {
        selectedLanguage = LocaleHelper.getLanguage(this)
    }

    private fun setupListeners() {
        btnBack.setOnClickListener {
            finish()
        }

        cardIndonesian.setOnClickListener {
            if (selectedLanguage != "in") {
                selectedLanguage = "in"
                changeLanguage("in")
            }
        }

        cardEnglish.setOnClickListener {
            if (selectedLanguage != "en") {
                selectedLanguage = "en"
                changeLanguage("en")
            }
        }
    }

    private fun changeLanguage(language: String) {
        LocaleHelper.setLocale(this, language)
        updateSelectionUI()

        // Restart activity to apply language change
        val intent = Intent(this, MainActivity::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        startActivity(intent)
        finish()
    }

    private fun updateSelectionUI() {
        when (selectedLanguage) {
            "in" -> {
                ivSelectedIndonesian.visibility = View.VISIBLE
                ivSelectedEnglish.visibility = View.GONE
            }
            "en" -> {
                ivSelectedIndonesian.visibility = View.GONE
                ivSelectedEnglish.visibility = View.VISIBLE
            }
        }
    }
}