package com.abztest.presentation.dialog

import android.app.Activity
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import com.abztest.R
import com.abztest.presentation.servise.promptUserToEnableAccessibilityService

@Composable
fun ShowEnableAccessibilityDialog(activity: Activity) {
    val context = LocalContext.current // Get the current context
    val showDialog = remember { mutableStateOf(true) } // State to control the visibility of the dialog

    // If the dialog should be shown
    if (showDialog.value) {
        AlertDialog(
            onDismissRequest = {}, // Prevent the dialog from being dismissed by tapping outside
            title = { Text(text = stringResource(R.string.enable_accessibility_service)) }, // Dialog title
            text = { Text(stringResource(R.string.description_dialog)) }, // Dialog description text
            confirmButton = {
                TextButton(
                    onClick = {
                        showDialog.value = false // Hide the dialog
                        promptUserToEnableAccessibilityService(context) // Prompt the user to enable accessibility service
                        showDialog.value = true // Show the dialog again
                    }
                ) {
                    Text(stringResource(R.string.yes)) // Confirm button text
                }
            },
            dismissButton = {
                TextButton(
                    onClick = {
                        showDialog.value = false // Hide the dialog
                        activity.finishAffinity() // Exit the application
                    }
                ) {
                    Text(stringResource(R.string.no)) // Dismiss button text
                }
            }
        )
    }
}
