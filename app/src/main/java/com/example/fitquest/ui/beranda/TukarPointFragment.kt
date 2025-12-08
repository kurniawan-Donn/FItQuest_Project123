package com.example.fitquest.ui.tukarpoint

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.fitquest.R
import com.example.fitquest.databinding.FragmentTukarpointBinding
import com.example.fitquest.ui.utils.TemaUtils

class TukarPointFragment : Fragment() {

    private var _binding: FragmentTukarpointBinding? = null
    private val binding get() = _binding!!

    // Data dummy untuk item penukaran
    private val daftarItemPenukaran = listOf(
        TukarPointItem(
            id = 1,
            namaItem = "Diskon 10%",
            deskripsi = "Diskon 10% semua produk di shopeefood",
            hargaPoin = 100,
            iconResId = R.drawable.ic_diskonikon
        ),
        TukarPointItem(
            id = 2,
            namaItem = "Voucher Kopi",
            deskripsi = "Voucher Kopi di Toko Kopi Kenangan",
            hargaPoin = 400,
            iconResId = R.drawable.ic_coffe
        ),
        TukarPointItem(
            id = 3,
            namaItem = "Gift Card",
            deskripsi = "Giftcard pembelian 25rb",
            hargaPoin = 1200,
            iconResId = R.drawable.ic_giftcard2
        ),
        TukarPointItem(
            id = 4,
            namaItem = "Voucher Pulsa",
            deskripsi = "Voucher Pulsa 50rb",
            hargaPoin = 2000,
            iconResId = R.drawable.ic_pulsa
        )
    )

    override fun onCreateView(
        inflater: LayoutInflater,
        wadah: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentTukarpointBinding.inflate(inflater, wadah, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        aturTampilan()
        aturPendengarKlik()
        muatDataPoint()
    }

    private fun aturTampilan() {
        // Atur tema sesuai preferensi
        TemaUtils.muatPreferensiTemaGelap(requireContext())
    }

    private fun muatDataPoint() {
        val jumlahPoin = 0 // Contoh data statis
        binding.pointamount.text = jumlahPoin.toString()
    }

    private fun aturPendengarKlik() {
        // Tombol kembali
        binding.backbutton.setOnClickListener {
            kembaliKeBeranda()
        }

        // Tombol lihat lainnya
        binding.btnLihatLainnya.setOnClickListener {
            tampilkanSemuaItem()
        }

        // Tombol tukar untuk setiap item
        binding.btntrade1.setOnClickListener { tukarItem(daftarItemPenukaran[0]) }
        binding.btntrade2.setOnClickListener { tukarItem(daftarItemPenukaran[1]) }
        binding.btntrade3.setOnClickListener { tukarItem(daftarItemPenukaran[2]) }
        binding.btntrade4.setOnClickListener { tukarItem(daftarItemPenukaran[3]) }
    }

    private fun kembaliKeBeranda() {
        // Gunakan Navigation Component untuk kembali
        findNavController().navigateUp()
    }

    private fun tukarItem(item: TukarPointItem) {
        // TODO: Implementasi logika penukaran poin
        // 1. Cek apakah poin mencukupi
        // 2. Kurangi poin pengguna
        // 3. Tambahkan item ke inventori
        // 4. Tampilkan konfirmasi

        tampilkanDialogKonfirmasi(item)
    }

    private fun tampilkanDialogKonfirmasi(item: TukarPointItem) {
        // TODO: Implementasi dialog konfirmasi
        // Contoh: AlertDialog dengan konfirmasi penukaran
        // Untuk sementara, tampilkan toast
        android.widget.Toast.makeText(
            requireContext(),
            "Menukar ${item.namaItem} dengan ${item.hargaPoin} poin",
            android.widget.Toast.LENGTH_SHORT
        ).show()
    }

    private fun tampilkanSemuaItem() {
        // TODO: Implementasi untuk menampilkan semua item penukaran
        // Bisa menggunakan dialog atau navigasi ke fragment lain
        android.widget.Toast.makeText(
            requireContext(),
            "Menampilkan semua item penukaran",
            android.widget.Toast.LENGTH_SHORT
        ).show()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}