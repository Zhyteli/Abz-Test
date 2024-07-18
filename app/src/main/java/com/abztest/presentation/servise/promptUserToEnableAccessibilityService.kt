package com.abztest.presentation.servise

import android.content.Context
import android.content.Intent
import android.provider.Settings

// Function to prompt the user to enable the accessibility service
fun promptUserToEnableAccessibilityService(context: Context) {
    // Create an intent to open the accessibility settings
    val intent = Intent(Settings.ACTION_ACCESSIBILITY_SETTINGS)

    // Set the intent flag to start a new task
    intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK

    // Start the accessibility settings activity
    context.startActivity(intent)
}
