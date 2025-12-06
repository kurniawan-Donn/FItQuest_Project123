package com.example.fitquest

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.graphics.Color
import android.os.Bundle
import androidx.activity.addCallback
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.appcompat.widget.SwitchCompat
import androidx.cardview.widget.CardView
import com.github.mikephil.charting.charts.LineChart
import com.github.mikephil.charting.components.XAxis
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.data.LineData
import com.github.mikephil.charting.data.LineDataSet
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter
import com.google.android.material.bottomnavigation.BottomNavigationView

class ProfileActivity : AppCompatActivity() {

    private lateinit var lineChart: LineChart
    private lateinit var switchDarkMode: SwitchCompat
    private lateinit var cardUbahBahasa: CardView
    private lateinit var cardSyaratKetentuan: CardView
    private lateinit var cardJenisPeta: CardView
    private lateinit var bottomNavigation: BottomNavigationView
    private lateinit var sharedPreferences: SharedPreferences

    companion object {
        private const val PREFS_NAME = "AppPreferences"
        private const val KEY_DARK_MODE = "dark_mode"
    }

    override fun onCreate(savedInstanceState: Bundle?) {

        // Load dark mode sebelum setContentView
        sharedPreferences = getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
        loadDarkModePreference()

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)

        // Inisialisasi views
        initViews()

        // Set switch sesuai preference
        updateSwitchState()

        // Listener
        setupListeners()

        // Grafik
        setupLineChart()

        // Bottom nav
        setupBottomNavigation()

        // Back button override
        onBackPressedDispatcher.addCallback(this) {
            val intent = Intent(this@ProfileActivity, MainActivity::class.java)
            startActivity(intent)
            this@ProfileActivity.setActivityTransition()
            finish()
        }
    }

    private fun initViews() {
        lineChart = findViewById(R.id.lineChart)
        switchDarkMode = findViewById(R.id.switchDarkMode)
        cardUbahBahasa = findViewById(R.id.cardUbahBahasa)
        cardSyaratKetentuan = findViewById(R.id.cardSyaratKetentuan)
        cardJenisPeta = findViewById(R.id.cardJenisPeta)
        bottomNavigation = findViewById(R.id.bottomNavigation)
    }

    private fun loadDarkModePreference() {
        val isDarkMode = sharedPreferences.getBoolean(KEY_DARK_MODE, false)
        AppCompatDelegate.setDefaultNightMode(
            if (isDarkMode) AppCompatDelegate.MODE_NIGHT_YES
            else AppCompatDelegate.MODE_NIGHT_NO
        )
    }

    private fun updateSwitchState() {
        val isDarkMode = sharedPreferences.getBoolean(KEY_DARK_MODE, false)
        switchDarkMode.isChecked = isDarkMode
    }

    private fun saveDarkModePreference(isDarkMode: Boolean) {
        sharedPreferences.edit().putBoolean(KEY_DARK_MODE, isDarkMode).apply()
    }

    private fun setupBottomNavigation() {
        bottomNavigation.selectedItemId = R.id.nav_profile

        bottomNavigation.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.nav_home -> {
                    startActivity(Intent(this, MainActivity::class.java))
                    setActivityTransition()
                    finish()
                    true
                }

                R.id.nav_mission -> {
                    startActivity(Intent(this, MissionActivity::class.java))
                    setActivityTransition()
                    finish()
                    true
                }

                R.id.nav_profile -> true
                else -> false
            }
        }
    }

    private fun setupLineChart() {
        val entries = arrayListOf(
            Entry(0f, 20f),
            Entry(1f, 70f),
            Entry(2f, 30f),
            Entry(3f, 40f),
            Entry(4f, 50f),
            Entry(5f, 90f),
            Entry(6f, 95f)
        )

        val dataSet = LineDataSet(entries, "% Progres").apply {
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

        lineChart.data = LineData(dataSet)
        lineChart.xAxis.apply {
            position = XAxis.XAxisPosition.BOTTOM
            valueFormatter = IndexAxisValueFormatter(
                arrayOf("Senin", "Selasa", "Rabu", "Kamis", "Jumat", "Sabtu", "Minggu")
            )
            granularity = 1f
            setDrawGridLines(true)
            gridColor = Color.parseColor("#E0E0E0")
            textColor = Color.parseColor("#666666")
            textSize = 10f
        }

        lineChart.axisLeft.apply {
            axisMinimum = 0f
            axisMaximum = 100f
            setDrawGridLines(true)
            gridColor = Color.parseColor("#E0E0E0")
            textColor = Color.parseColor("#666666")
            textSize = 10f
        }

        lineChart.axisRight.isEnabled = false

        lineChart.apply {
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

    private fun setupListeners() {
        switchDarkMode.setOnCheckedChangeListener { _, isChecked ->
            saveDarkModePreference(isChecked)
            AppCompatDelegate.setDefaultNightMode(
                if (isChecked) AppCompatDelegate.MODE_NIGHT_YES
                else AppCompatDelegate.MODE_NIGHT_NO
            )
        }

        cardUbahBahasa.setOnClickListener { /* TODO */ }
        cardSyaratKetentuan.setOnClickListener { /* TODO */ }
        cardJenisPeta.setOnClickListener { /* TODO */ }
    }
}
