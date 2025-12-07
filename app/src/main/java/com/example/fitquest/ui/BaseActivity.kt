package com.example.fitquest.ui

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import com.example.fitquest.ui.utils.LocaleHelper

/**
 * Base Activity yang akan di-extend oleh semua Activity
 * untuk memastikan bahasa yang dipilih diterapkan
 */
open class BaseActivity : AppCompatActivity() {

    override fun attachBaseContext(newBase: Context) {
        super.attachBaseContext(LocaleHelper.onAttach(newBase))
    }
}