package com.example.fitquest.ui.utils

import android.content.Context
import android.content.SharedPreferences

object ManajerPreferensi {
    private const val NAMA_PREFERENSI = "PreferensiAplikasi"

    fun dapatkanPreferensi(konteks: Context): SharedPreferences {
        return konteks.getSharedPreferences(NAMA_PREFERENSI, Context.MODE_PRIVATE)
    }
}