package com.example.fitquest.ui.misi

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.fitquest.databinding.FragmentMisiBinding

class MissionFragment : Fragment() {

    private var _binding: FragmentMisiBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        wadah: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMisiBinding.inflate(inflater, wadah, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        aturUI()
    }

    private fun aturUI() {
        binding.tvJudulMisi.text = "Misi"
        binding.tvDeskripsiMisi.text = "Halaman Misi"
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}