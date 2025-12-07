package com.example.fitquest.ui.utils

import android.content.Context
import androidx.appcompat.app.AppCompatDelegate

object TemaUtils {

    fun muatPreferensiTemaGelap(konteks: Context) {
        val preferensi = ManajerPreferensi.dapatkanPreferensi(konteks)
        val modeGelap = preferensi.getBoolean("mode_gelap", false)

        AppCompatDelegate.setDefaultNightMode(
            if (modeGelap) AppCompatDelegate.MODE_NIGHT_YES
            else AppCompatDelegate.MODE_NIGHT_NO
        )
    }
}