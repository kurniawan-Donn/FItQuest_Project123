package com.example.fitquest

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.bottomnavigation.BottomNavigationView

class ShopActivity : AppCompatActivity() {

    private lateinit var bottomNavigation: BottomNavigationView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_shop)

        // Initialize bottom navigation
        bottomNavigation = findViewById(R.id.bottomNavigation)

        // Set Shop as selected
        bottomNavigation.selectedItemId = R.id.nav_shop

        // Setup bottom navigation listener
        setupBottomNavigation()

        // Handle back button with modern API
        onBackPressedDispatcher.addCallback(this, object : androidx.activity.OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                // Go back to home instead of closing app
                val intent = Intent(this@ShopActivity, MainActivity::class.java)
                startActivity(intent)
                this@ShopActivity.setActivityTransition()
                finish()
            }
        })
    }

    private fun setupBottomNavigation() {
        bottomNavigation.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.nav_home -> {
                    val intent = Intent(this, MainActivity::class.java)
                    startActivity(intent)
                    setActivityTransition()
                    finish()
                    true
                }
                R.id.nav_shop -> {
                    // Already on shop, do nothing
                    true
                }
                R.id.nav_mission -> {
                    val intent = Intent(this, MissionActivity::class.java)
                    startActivity(intent)
                    setActivityTransition()
                    finish()
                    true
                }
                R.id.nav_profile -> {
                    val intent = Intent(this, ProfileActivity::class.java)
                    startActivity(intent)
                    setActivityTransition()
                    finish()
                    true
                }
                else -> false
            }
        }
    }
}