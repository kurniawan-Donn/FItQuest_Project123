package com.example.fitquest.ui.misi

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.fitquest.R
import com.example.fitquest.databinding.FragmentMisiBinding
import com.example.fitquest.ui.utils.TemaUtils

class MissionFragment : Fragment() {

    private var _binding: FragmentMisiBinding? = null
    private val binding get() = _binding!!

    // Data misi harian
    private val daftarMisi = listOf(
        MissionItem(
            id = 1,
            nama = "Lari 150 m",
            deskripsi = "Lari sejauh 150 meter",
            hadiahPoin = 5,
            targetJarak = 150.0, // meter
            iconResId = R.drawable.ic_runner
        ),
        MissionItem(
            id = 2,
            nama = "Lari 300 m",
            deskripsi = "Lari sejauh 300 meter",
            hadiahPoin = 10,
            targetJarak = 300.0,
            iconResId = R.drawable.ic_runner
        ),
        MissionItem(
            id = 3,
            nama = "Lari 500 m",
            deskripsi = "Lari sejauh 500 meter",
            hadiahPoin = 15,
            targetJarak = 500.0,
            iconResId = R.drawable.ic_runner
        ),
        MissionItem(
            id = 4,
            nama = "Lari 1 km",
            deskripsi = "Lari sejauh 1 kilometer",
            hadiahPoin = 25,
            targetJarak = 1000.0,
            iconResId = R.drawable.ic_runner
        ),
        MissionItem(
            id = 5,
            nama = "Lari 2 km",
            deskripsi = "Lari sejauh 2 kilometer",
            hadiahPoin = 50,
            targetJarak = 2000.0,
            iconResId = R.drawable.ic_runner
        )
    )

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMisiBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        aturTampilan()
        aturPendengarKlik()
        muatStatusMisi()
    }

    private fun aturTampilan() {
        // Atur tema sesuai preferensi
        TemaUtils.muatPreferensiTemaGelap(requireContext())
    }

    private fun aturPendengarKlik() {
        // Tombol kembali (tidak diperlukan karena menggunakan bottom navigation)
        binding.backbutton.visibility = View.GONE

        // Tombol mulai untuk setiap misi
        binding.btnMulai1.setOnClickListener { mulaiMisi(daftarMisi[0]) }
        binding.btnMulai2.setOnClickListener { mulaiMisi(daftarMisi[1]) }
        binding.btnMulai3.setOnClickListener { mulaiMisi(daftarMisi[2]) }
        binding.btnMulai4.setOnClickListener { mulaiMisi(daftarMisi[3]) }
        binding.btnMulai5.setOnClickListener { mulaiMisi(daftarMisi[4]) }
    }

    private fun muatStatusMisi() {
        // TODO: Ambil status misi dari SharedPreferences/ViewModel
        // Untuk sekarang, semua misi tersedia
    }

    private fun mulaiMisi(misi: MissionItem) {
        // Simpan misi yang dipilih ke SharedPreferences
        val sharedPrefs = requireContext().getSharedPreferences("misi_prefs", 0)
        sharedPrefs.edit().apply {
            putInt("selected_mission_id", misi.id)
            putString("selected_mission_name", misi.nama)
            putFloat("selected_mission_distance", misi.targetJarak.toFloat())
            putInt("selected_mission_reward", misi.hadiahPoin)
            apply()
        }

        // Navigasi ke StartMissionFragment menggunakan action
        findNavController().navigate(R.id.action_misiFragment_to_startMissionFragment)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}