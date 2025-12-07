package com.example.fitquest.ui.beranda

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.fitquest.databinding.FragmentBerandaBinding
import com.example.fitquest.R

class HomeFragment : Fragment() {

    private var _binding: FragmentBerandaBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        // PERBAIKAN: Ganti 'wadah' menjadi 'container' (konvensi)
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentBerandaBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        aturUI()
        aturPendengarKlik()
        aturIndikatorProgres()
    }

    private fun aturUI() {
        // SUDAH BENAR: Menggunakan string resource yang sesuai
        // tvSapaan menggunakan R.string.greeting
        binding.tvSapaan.text = getString(R.string.greeting)
        // tvPoin menggunakan format string R.string.points
        binding.tvPoin.text = getString(R.string.points) // Angka '0' akan mengganti %d
        // tvPersentaseProgres menggunakan format string R.string.progress_percentage
        binding.tvPersentaseProgres.text = getString(R.string.progress_percentage, 65) // Angka '65' akan mengganti %d
        // tvTargetHarian menggunakan format string R.string.daily_target, dan string lain R.string.daily_target_value sebagai parameter %s ))
    }

    private fun aturPendengarKlik() {
        // Listener untuk tombol dan item menu
        binding.btnMulaiMisi.setOnClickListener {
            // TODO: Navigasi ke halaman mulai misi
            // findNavController().navigate(R.id.action_to_mission)
        }

        binding.klaimHadiah.setOnClickListener {
            // TODO: Navigasi ke halaman klaim hadiah
        }

        binding.tukarPoin.setOnClickListener {
            // TODO: Navigasi ke halaman tukar poin
        }

        binding.voucherDiskon.setOnClickListener {
            // TODO: Navigasi ke halaman voucher
        }

        binding.kartuHadiah.setOnClickListener {
            // TODO: Navigasi ke halaman kartu hadiah
        }
    }

    private fun aturIndikatorProgres() {
        // Contoh: Set progres menjadi 65%
        binding.indikatorProgres.progress = 65
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}