package com.example.fitquest.ui.beranda

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.fitquest.databinding.FragmentBerandaBinding
import com.example.fitquest.R

class HomeFragment : Fragment() {

    private var _binding: FragmentBerandaBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        wadah: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentBerandaBinding.inflate(inflater, wadah, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        aturUI()
        aturPendengarKlik()
        aturIndikatorProgres()
    }

    private fun aturUI() {
        // Gunakan string resource
        binding.tvSapaan.text = getString(R.string.greeting)
        binding.tvPoin.text = "0 Poin"
        binding.tvPersentaseProgres.text = "65%"
        binding.tvTargetHarian.text = "Target harian 10km"
    }

    private fun aturPendengarKlik() {
        binding.btnMulaiMisi.setOnClickListener {
            // TODO: Navigasi ke halaman mulai misi
        }

        binding.klaimHadiah.setOnClickListener {
            // TODO: Navigasi ke halaman klaim hadiah
        }

        binding.tukarPoin.setOnClickListener {
            // Navigasi ke TukarPointFragment
            navigasiKeTukarPoint()
        }

        binding.voucherDiskon.setOnClickListener {
            // TODO: Navigasi ke halaman voucher
        }

        binding.kartuHadiah.setOnClickListener {
            // TODO: Navigasi ke halaman kartu hadiah
        }
    }

    private fun navigasiKeTukarPoint() {
        // Gunakan Navigation Component untuk navigasi
        findNavController().navigate(R.id.tukarPointFragment)
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