package com.example.fitquest

import android.graphics.Color
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SwitchCompat
import androidx.cardview.widget.CardView
import com.github.mikephil.charting.charts.LineChart
import com.github.mikephil.charting.components.XAxis
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.data.LineData
import com.github.mikephil.charting.data.LineDataSet
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter

class ProfileActivity : AppCompatActivity() {

    private lateinit var lineChart: LineChart
    private lateinit var switchDarkMode: SwitchCompat
    private lateinit var cardUbahBahasa: CardView
    private lateinit var cardSyaratKetentuan: CardView
    private lateinit var cardJenisPeta: CardView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)

        // Inisialisasi views
        initViews()

        // Setup grafik
        setupLineChart()

        // Setup listeners
        setupListeners()
    }

    private fun initViews() {
        lineChart = findViewById(R.id.lineChart)
        switchDarkMode = findViewById(R.id.switchDarkMode)
        cardUbahBahasa = findViewById(R.id.cardUbahBahasa)
        cardSyaratKetentuan = findViewById(R.id.cardSyaratKetentuan)
        cardJenisPeta = findViewById(R.id.cardJenisPeta)
    }

    private fun setupLineChart() {
        // Data progress per hari (Senin sampai Minggu)
        val entries = arrayListOf(
            Entry(0f, 20f),  // Senin
            Entry(1f, 70f),  // Selasa
            Entry(2f, 30f),  // Rabu
            Entry(3f, 40f),  // Kamis
            Entry(4f, 50f),  // Jumat
            Entry(5f, 90f),  // Sabtu
            Entry(6f, 95f)   // Minggu
        )

        // Dataset
        val dataSet = LineDataSet(entries, "% Progres")

        // Styling dataset
        dataSet.color = Color.parseColor("#673AB7") // Warna ungu
        dataSet.setCircleColor(Color.parseColor("#673AB7"))
        dataSet.circleRadius = 5f
        dataSet.circleHoleRadius = 3f
        dataSet.lineWidth = 2.5f
        dataSet.valueTextSize = 0f // Sembunyikan nilai di atas titik
        dataSet.mode = LineDataSet.Mode.CUBIC_BEZIER // Garis melengkung
        dataSet.setDrawFilled(true) // Aktifkan area fill
        dataSet.fillColor = Color.parseColor("#E1BEE7") // Warna fill ungu muda
        dataSet.fillAlpha = 180

        // Line data
        val lineData = LineData(dataSet)
        lineChart.data = lineData

        // Label hari
        val labels = arrayOf("Senin", "Selasa", "Rabu", "Kamis", "Jumat", "Sabtu", "Minggu")

        // Konfigurasi X-Axis
        val xAxis = lineChart.xAxis
        xAxis.position = XAxis.XAxisPosition.BOTTOM
        xAxis.valueFormatter = IndexAxisValueFormatter(labels)
        xAxis.granularity = 1f
        xAxis.setDrawGridLines(true)
        xAxis.gridColor = Color.parseColor("#E0E0E0")
        xAxis.textColor = Color.parseColor("#666666")
        xAxis.textSize = 10f

        // Konfigurasi Y-Axis kiri
        val yAxisLeft = lineChart.axisLeft
        yAxisLeft.axisMinimum = 0f
        yAxisLeft.axisMaximum = 100f
        yAxisLeft.setDrawGridLines(true)
        yAxisLeft.gridColor = Color.parseColor("#E0E0E0")
        yAxisLeft.textColor = Color.parseColor("#666666")
        yAxisLeft.textSize = 10f

        // Sembunyikan Y-Axis kanan
        lineChart.axisRight.isEnabled = false

        // Konfigurasi chart
        lineChart.description.isEnabled = false
        lineChart.legend.isEnabled = false
        lineChart.setTouchEnabled(true)
        lineChart.isDragEnabled = true
        lineChart.setScaleEnabled(false)
        lineChart.setPinchZoom(false)
        lineChart.setDrawGridBackground(false)
        lineChart.animateX(1000)

        // Refresh chart
        lineChart.invalidate()
    }

    private fun setupListeners() {
        // Dark Mode Toggle
        switchDarkMode.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                // TODO: Implement dark mode
                // AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
            } else {
                // TODO: Implement light mode
                // AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
            }
        }

        // Ubah Bahasa
        cardUbahBahasa.setOnClickListener {
            // TODO: Navigate to language selection activity
            // startActivity(Intent(this, LanguageActivity::class.java))
        }

        // Syarat & Ketentuan
        cardSyaratKetentuan.setOnClickListener {
            // TODO: Navigate to terms and conditions activity
            // startActivity(Intent(this, TermsActivity::class.java))
        }

        // Jenis Peta
        cardJenisPeta.setOnClickListener {
            // TODO: Navigate to map type selection activity
            // startActivity(Intent(this, MapTypeActivity::class.java))
        }
    }
}