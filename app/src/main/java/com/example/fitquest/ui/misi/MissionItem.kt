package com.example.fitquest.ui.misi

data class MissionItem(
    val id: Int,
    val nama: String,
    val deskripsi: String,
    val hadiahPoin: Int,
    val targetJarak: Double, // dalam meter
    val iconResId: Int
)