package com.example.fitquest.ui

import android.content.Context
import android.os.Bundle
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.example.fitquest.R
import com.example.fitquest.databinding.ActivityMainBinding
import com.example.fitquest.ui.utils.LocaleHelper
import com.example.fitquest.ui.utils.TemaUtils

class MainActivity : BaseActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var navController: NavController

    override fun attachBaseContext(newBase: Context) {
        super.attachBaseContext(LocaleHelper.onAttach(newBase))
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Muat preferensi tema sebelum mengatur konten
        TemaUtils.muatPreferensiTemaGelap(this)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        aturNavigasi()
    }

    private fun aturNavigasi() {
        // Dapatkan NavHostFragment
        val navHostFragment = supportFragmentManager
            .findFragmentById(R.id.navHostFragment) as NavHostFragment

        // Dapatkan NavController
        navController = navHostFragment.navController

        // Hubungkan BottomNavigationView dengan NavController
        binding.bottomNavigation.setupWithNavController(navController)
    }
}