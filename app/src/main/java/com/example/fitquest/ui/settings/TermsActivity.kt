package com.example.fitquest.ui.settings

import android.content.Context
import android.os.Bundle
import android.text.method.LinkMovementMethod
import androidx.appcompat.app.AppCompatActivity
import androidx.core.text.HtmlCompat
import com.example.fitquest.R
import com.example.fitquest.databinding.ActivityTermsBinding
import com.example.fitquest.ui.utils.LocaleHelper

class TermsActivity : AppCompatActivity() {

    // 1. Setup View Binding
    private lateinit var binding: ActivityTermsBinding

    // Pastikan string resource ini (R.string.terms_usage) berisi teks HTML
    // Contoh di strings.xml: <string name="terms_usage"><![CDATA[ <h2>Judul</h2> <p>Isi paragraf...</p> ]]></string>
    private val termsStringResId = R.string.terms_content

    override fun attachBaseContext(newBase: Context) {
        // Konfigurasi bahasa (Locale)
        super.attachBaseContext(LocaleHelper.onAttach(newBase))
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // 2. Inflate Layout
        binding = ActivityTermsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupListeners()
        displayTermsContent()
    }

    private fun setupListeners() {
        // Tombol Back di Header
        binding.btnBack.setOnClickListener {
            onBackPressedDispatcher.onBackPressed() // Lebih modern daripada finish() untuk navigasi balik
        }

        // Tombol Kembali di Bawah
        binding.btnKembali.setOnClickListener {
            onBackPressedDispatcher.onBackPressed()
        }
    }

    private fun displayTermsContent() {
        // Ambil string raw dari resources
        val htmlText = getString(termsStringResId)

        // Konversi string HTML ke CharSequence agar tag HTML (bold, italic, link) terbaca
        val formattedText = HtmlCompat.fromHtml(
            htmlText,
            HtmlCompat.FROM_HTML_MODE_LEGACY
        )

        // Set teks ke TextView
        // PENTING: Pastikan ID di XML adalah android:id="@+id/TermsContent"
        binding.TermsContent.text = formattedText

        // Aktifkan agar link (tag <a>) bisa diklik
        binding.TermsContent.movementMethod = LinkMovementMethod.getInstance()
    }
}
