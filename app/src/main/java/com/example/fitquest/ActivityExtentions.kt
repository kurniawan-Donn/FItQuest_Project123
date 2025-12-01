package com.example.fitquest

import android.app.Activity
import android.os.Build

/**
 * Extension function untuk handle activity transition
 * Kompatibel dengan semua API level
 */
@Suppress("DEPRECATION")
fun Activity.setActivityTransition(enterAnim: Int = 0, exitAnim: Int = 0) {
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.UPSIDE_DOWN_CAKE) {
        // API 34+ menggunakan overrideActivityTransition
        overrideActivityTransition(Activity.OVERRIDE_TRANSITION_OPEN, enterAnim, exitAnim)
    } else {
        // API < 34 menggunakan overridePendingTransition
        overridePendingTransition(enterAnim, exitAnim)
    }
}