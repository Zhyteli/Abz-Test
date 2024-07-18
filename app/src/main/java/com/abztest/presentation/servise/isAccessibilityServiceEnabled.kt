package com.abztest.presentation.servise

import android.accessibilityservice.AccessibilityService
import android.content.ComponentName
import android.content.Context
import android.provider.Settings
import android.text.TextUtils
import android.view.accessibility.AccessibilityManager

// Function to check if a specific AccessibilityService is enabled
fun isAccessibilityServiceEnabled(context: Context, service: Class<out AccessibilityService>): Boolean {
    // Get the AccessibilityManager system service
    val am = context.getSystemService(Context.ACCESSIBILITY_SERVICE) as AccessibilityManager

    // Retrieve the list of enabled accessibility services from the system settings
    val enabledServices = Settings.Secure.getString(context.contentResolver, Settings.Secure.ENABLED_ACCESSIBILITY_SERVICES)

    // Create a splitter to split the enabled services string by colon (':')
    val colonSplitter = TextUtils.SimpleStringSplitter(':')
    colonSplitter.setString(enabledServices)

    // Iterate over each enabled service component name
    while (colonSplitter.hasNext()) {
        val componentName = ComponentName.unflattenFromString(colonSplitter.next())

        // Check if the component name matches the specified service
        if (componentName != null && componentName.packageName == context.packageName && componentName.className == service.name) {
            return true // Service is enabled
        }
    }
    return false // Service is not enabled
}
