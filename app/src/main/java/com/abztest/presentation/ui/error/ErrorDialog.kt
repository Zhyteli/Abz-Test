package com.abztest.presentation.ui.error

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun ErrorDialog(message: String) {
    var showError by remember { mutableStateOf(true) } // State to control the visibility of the error dialog

    // If the error dialog should be shown
    if (showError) {
        Box(modifier = Modifier.fillMaxSize()) {
            Card(
                shape = RoundedCornerShape(8.dp), // Rounded corners for the card
                colors = CardDefaults.cardColors(
                    containerColor = Color(0xFFFFCDD2), // Background color of the card
                    contentColor = Color(0xFFB71C1C)    // Content color of the card
                ),
                modifier = Modifier
                    .fillMaxWidth()                // Card fills the width of the screen
                    .align(Alignment.Center)       // Card is centered in the box
                    .padding(16.dp)                // Padding around the card
            ) {
                Column(
                    modifier = Modifier
                        .background(Color(0xFFFFCDD2)) // Background color of the column
                        .padding(16.dp)                // Padding inside the column
                ) {
                    Text(
                        text = "Error",               // Title of the dialog
                        fontSize = 20.sp,             // Font size for the title
                        fontWeight = FontWeight.Bold  // Font weight for the title
                    )
                    Spacer(modifier = Modifier.height(8.dp)) // Spacer for spacing between title and message
                    Text(text = message)                   // Error message text
                    Spacer(modifier = Modifier.height(16.dp)) // Spacer for spacing between message and button
                    Button(
                        onClick = { showError = false }, // On click, dismiss the error dialog
                        modifier = Modifier.align(Alignment.End), // Button aligned to the end
                        colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFB71C1C)) // Button colors
                    ) {
                        Text(text = "Dismiss", color = Color.White) // Button text
                    }
                }
            }
        }
    }
}


@Preview
@Composable
fun ErrorDialogPreview() {
    ErrorDialog("ERROR")
}