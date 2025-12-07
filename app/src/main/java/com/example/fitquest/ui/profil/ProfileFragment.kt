package com.example.fitquest.ui.profil

import android.content.Intent
import android.content.SharedPreferences
import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatDelegate
import androidx.fragment.app.Fragment
import com.example.fitquest.databinding.FragmentProfilBinding
import com.example.fitquest.ui.settings.LanguageActivity
import com.example.fitquest.ui.settings.TermsActivity
import com.example.fitquest.ui.utils.ManajerPreferensi
import com.github.mikephil.charting.components.XAxis
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.data.LineData
import com.github.mikephil.charting.data.LineDataSet
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter

class ProfileFragment : Fragment() {

    private var _binding: FragmentProfilBinding? = null
    private val binding get() = _binding!!
    private lateinit var preferensi: SharedPreferences

    override fun onCreateView(
        inflater: LayoutInflater,
        wadah: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentProfilBinding.inflate(inflater, wadah, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        preferensi = ManajerPreferensi.dapatkanPreferensi(requireContext())

        aturGrafik()
        aturSaklarModeGelap()
        aturPendengarKlik()
    }

    private fun aturGrafik() {
        val data = arrayListOf(
            Entry(0f, 20f),
            Entry(1f, 70f),
            Entry(2f, 30f),
            Entry(3f, 40f),
            Entry(4f, 50f),
            Entry(5f, 90f),
            Entry(6f, 95f)
        )

        val setData = LineDataSet(data, "% Progres").apply {
            color = Color.parseColor("#673AB7")
            setCircleColor(Color.parseColor("#673AB7"))
            circleRadius = 5f
            circleHoleRadius = 3f
            lineWidth = 2.5f
            valueTextSize = 0f
            mode = LineDataSet.Mode.CUBIC_BEZIER
            setDrawFilled(true)
            fillColor = Color.parseColor("#E1BEE7")
            fillAlpha = 180
        }

        binding.grafikgaris.data = LineData(setData)

        // Get days of week from string array resources
        val daysOfWeek = resources.getStringArray(com.example.fitquest.R.array.days_of_week)

        binding.grafikgaris.xAxis.apply {
            position = XAxis.XAxisPosition.BOTTOM
            valueFormatter = IndexAxisValueFormatter(daysOfWeek)
            granularity = 1f
            setDrawGridLines(true)
            gridColor = Color.parseColor("#E0E0E0")
            textColor = Color.parseColor("#666666")
            textSize = 10f
        }

        binding.grafikgaris.axisLeft.apply {
            axisMinimum = 0f
            axisMaximum = 100f
            setDrawGridLines(true)
            gridColor = Color.parseColor("#E0E0E0")
            textColor = Color.parseColor("#666666")
            textSize = 10f
        }

        binding.grafikgaris.axisRight.isEnabled = false

        binding.grafikgaris.apply {
            description.isEnabled = false
            legend.isEnabled = false
            setTouchEnabled(true)
            isDragEnabled = true
            setScaleEnabled(false)
            setPinchZoom(false)
            setDrawGridBackground(false)
            animateX(1000)
            invalidate()
        }
    }

    private fun aturSaklarModeGelap() {
        val modeGelap = preferensi.getBoolean("mode_gelap", false)
        binding.switchDarkMode.isChecked = modeGelap

        binding.switchDarkMode.setOnCheckedChangeListener { _, dicentang ->
            preferensi.edit().apply {
                putBoolean("mode_gelap", dicentang)
                apply()
            }

            AppCompatDelegate.setDefaultNightMode(
                if (dicentang) AppCompatDelegate.MODE_NIGHT_YES
                else AppCompatDelegate.MODE_NIGHT_NO
            )
        }
    }

    private fun aturPendengarKlik() {
        binding.cardUbahBahasa.setOnClickListener {
            // Navigasi ke LanguageActivity
            val intent = Intent(requireContext(), LanguageActivity::class.java)
            startActivity(intent)
        }

        binding.cardSyaratKetentuan.setOnClickListener {
            // Navigasi ke TermsActivity
            val intent = Intent(requireContext(), TermsActivity::class.java)
            startActivity(intent)
        }

        binding.cardJenisPeta.setOnClickListener {
            // TODO: Navigasi ke pengaturan peta
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}