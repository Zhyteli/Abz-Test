package com.abztest.presentation.ui.item

import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.abztest.presentation.model.RequestUi
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

@Composable
fun RequestItem(
    requestUi: RequestUi
) {
    // Create a Card composable to represent the request item
    Card(
        modifier = Modifier
            .padding(8.dp) // Add padding around the card
            .fillMaxWidth(), // Make the card fill the available width
        shape = RoundedCornerShape(16.dp), // Use rounded corners for the card
        colors = CardDefaults.cardColors(
            containerColor = Color(0xFFFFCCCC) // Set the background color of the card
        )
    ) {
        // Create a Column composable to arrange elements vertically
        Column(
            modifier = Modifier
                .padding(16.dp) // Add padding inside the column
        ) {
            // Display the request text
            Text(
                text = requestUi.request,
                style = MaterialTheme.typography.headlineSmall.copy(
                    fontSize = 20.sp, // Set font size to 20sp
                    fontWeight = FontWeight.Bold // Set font weight to bold
                )
            )
            // Display the timestamp as a formatted date string
            Text(
                text = convertMillisToDateString(requestUi.timestamp),
                style = MaterialTheme.typography.bodyMedium.copy(
                    fontSize = 16.sp // Set font size to 16sp
                ),
                color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.6f) // Set text color with alpha
            )
            // Display the URL if it's not "null"
            if (requestUi.url != "null") {
                val annotatedString = buildAnnotatedString {
                    val url = "https://${requestUi.url}"
                    append(url) // Append the URL to the annotated string
                    addStyle(
                        style = SpanStyle(
                            color = MaterialTheme.colorScheme.primary, // Set text color to primary color
                            textDecoration = TextDecoration.Underline // Underline the text
                        ), start = 0, end = url.length
                    )
                    addStringAnnotation(
                        tag = "URL", // Add a string annotation with the tag "URL"
                        annotation = url, // The annotation is the URL itself
                        start = 0, end = url.length
                    )
                }
                val context = LocalContext.current // Get the current context
                // Create a ClickableText composable to make the URL clickable
                ClickableText(
                    text = annotatedString,
                    style = MaterialTheme.typography.bodyMedium.copy(
                        fontSize = 16.sp // Set font size to 16sp
                    ),
                    onClick = { offset ->
                        annotatedString.getStringAnnotations(
                            tag = "URL", start = offset, end = offset
                        ).firstOrNull()?.let { annotation ->
                            val intent = Intent(Intent.ACTION_VIEW, Uri.parse(annotation.item)) // Create an intent to open the URL
                            context.startActivity(intent) // Start the activity to open the URL
                        }
                    }
                )
            }
        }
    }
}

// Function to convert milliseconds to a formatted date string
fun convertMillisToDateString(millis: Long): String {
    // Create a SimpleDateFormat instance with the desired format
    val dateFormat = SimpleDateFormat("hh:mm:ss a dd-MM-yyyy", Locale.getDefault())

    // Create a Date object from the milliseconds
    val date = Date(millis)

    // Format the date object into a string
    return dateFormat.format(date)
}

@Preview
@Composable
fun RequestItemPreview() {
    RequestItem(RequestUi(0, "google.com", 155555, "google.com/?rr"))
}

