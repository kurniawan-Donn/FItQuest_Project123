package com.example.fitquest.ui.tukarpoint

data class TukarPointItem(
    val id: Int,
    val namaItem: String,
    val deskripsi: String,
    val hargaPoin: Int,
    val iconResId: Int,
    val warnaGaris: String = "#009AD2"
)