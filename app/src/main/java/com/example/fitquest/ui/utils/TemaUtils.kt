package com.example.fitquest.ui.utils

import android.content.Context
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatDelegate

object TemaUtils {
    private const val NAMA_PREFERENSI = "PreferensiAplikasi"
    private const val KUNCI_MODE_GELAP = "mode_gelap"

    private fun dapatkanPreferensi(konteks: Context): SharedPreferences {
        return konteks.getSharedPreferences(NAMA_PREFERENSI, Context.MODE_PRIVATE)
    }

    fun muatPreferensiTemaGelap(konteks: Context) {
        val preferensi = dapatkanPreferensi(konteks)
        val modeGelap = preferensi.getBoolean(KUNCI_MODE_GELAP, false)

        AppCompatDelegate.setDefaultNightMode(
            if (modeGelap) AppCompatDelegate.MODE_NIGHT_YES
            else AppCompatDelegate.MODE_NIGHT_NO
        )
    }

    fun simpanPreferensiTemaGelap(konteks: Context, modeGelap: Boolean) {
        val preferensi = dapatkanPreferensi(konteks)
        preferensi.edit().apply {
            putBoolean(KUNCI_MODE_GELAP, modeGelap)
            apply()
        }
    }
}