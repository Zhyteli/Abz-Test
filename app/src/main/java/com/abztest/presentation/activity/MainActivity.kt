package com.abztest.presentation.activity

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.addCallback
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.abztest.presentation.dialog.ShowEnableAccessibilityDialog
import com.abztest.presentation.servise.BrowserAccessibilityService
import com.abztest.presentation.servise.isAccessibilityServiceEnabled
import com.abztest.presentation.ui.RequestListScreen
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            if (!isAccessibilityServiceEnabled(this, BrowserAccessibilityService::class.java)) {
                ShowEnableAccessibilityDialog(this)
                onBackPressedDispatcher.addCallback { }
            } else {
                RequestListScreen()
            }
        }
    }


    override fun onResume() {
        super.onResume()
        if (isAccessibilityServiceEnabled(this, BrowserAccessibilityService::class.java)) {
            setContent {
                RequestListScreen()
            }
        } else {
            setContent {
                ShowEnableAccessibilityDialog(this)
            }
        }
    }
}



