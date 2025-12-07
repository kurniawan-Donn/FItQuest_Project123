package com.example.fitquest.ui.profil

import android.content.SharedPreferences
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatDelegate
import androidx.core.graphics.toColorInt
import androidx.fragment.app.Fragment
import com.example.fitquest.databinding.FragmentProfilBinding
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

        inisialisasiKomponen()
        aturGrafik()
        aturSaklarModeGelap()
        aturPendengarKlik()
    }

    private fun inisialisasiKomponen() {
        // Inisialisasi komponen lain jika diperlukan
        // binding.tvNamaPengguna.text = "Hi, Pengguna"
    }

    private fun aturGrafik() {
        val grafik = binding.lineChart

        val data = arrayListOf(
            Entry(0f, 20f),
            Entry(1f, 70f),
            Entry(2f, 30f),
            Entry(3f, 40f),
            Entry(4f, 50f),
            Entry(5f, 90f),
            Entry(6f, 95f)
        )

        // GUNAKAN EXTENSION FUNCTION KTX
        val warnaUngu = "#673AB7".toColorInt()
        val warnaUnguMuda = "#E1BEE7".toColorInt()
        val warnaGrid = "#E0E0E0".toColorInt()
        val warnaText = "#666666".toColorInt()

        val setData = LineDataSet(data, "% Progres").apply {
            color = warnaUngu
            setCircleColor(warnaUngu)
            circleRadius = 5f
            circleHoleRadius = 3f
            lineWidth = 2.5f
            valueTextSize = 0f
            mode = LineDataSet.Mode.CUBIC_BEZIER
            setDrawFilled(true)
            fillColor = warnaUnguMuda
            fillAlpha = 180
        }

        grafik.data = LineData(setData)
        grafik.xAxis.apply {
            position = XAxis.XAxisPosition.BOTTOM
            valueFormatter = IndexAxisValueFormatter(
                arrayOf("Senin", "Selasa", "Rabu", "Kamis", "Jumat", "Sabtu", "Minggu")
            )
            granularity = 1f
            setDrawGridLines(true)
            gridColor = warnaGrid
            textColor = warnaText
            textSize = 10f
        }

        grafik.axisLeft.apply {
            axisMinimum = 0f
            axisMaximum = 100f
            setDrawGridLines(true)
            gridColor = warnaGrid
            textColor = warnaText
            textSize = 10f
        }

        grafik.axisRight.isEnabled = false

        grafik.apply {
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
        val saklar = binding.switchDarkMode

        val modeGelap = preferensi.getBoolean("mode_gelap", false)
        saklar.isChecked = modeGelap

        saklar.setOnCheckedChangeListener { _, dicentang ->
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
            // TODO: Navigasi ke pengaturan bahasa
            // findNavController().navigate(R.id.action_to_language)
        }

        binding.cardSyaratKetentuan.setOnClickListener {
            // TODO: Navigasi ke syarat dan ketentuan
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