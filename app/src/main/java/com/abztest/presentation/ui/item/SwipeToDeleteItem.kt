package com.abztest.presentation.ui.item

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.DismissDirection
import androidx.compose.material3.DismissState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.SwipeToDismiss
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.abztest.presentation.model.RequestUi

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SwipeToDeleteItem(
    request: RequestUi,
    dismissState: DismissState
) {
    // Composable that provides swipe-to-dismiss functionality
    SwipeToDismiss(
        state = dismissState, // Pass the dismiss state
        background = {
            val color = Color.Red.copy(alpha = 0.5f) // Background color with transparency
            val direction = dismissState.dismissDirection ?: return@SwipeToDismiss

            // Background content of the swipe-to-dismiss
            Box(
                modifier = Modifier
                    .padding(8.dp) // Padding around the background
                    .fillMaxSize() // Fill the available space
            ) {
                // Card that serves as the background
                Card(
                    shape = RoundedCornerShape(16.dp), // Rounded corners for the card
                    colors = CardDefaults.cardColors(
                        containerColor = color // Background color of the card
                    ),
                    modifier = Modifier
                        .fillMaxSize() // Fill the available space
                        .align(Alignment.Center) // Center the card within the box
                ) {
                    // Box to hold the icon
                    Box(
                        modifier = Modifier.fillMaxSize() // Fill the available space
                    ) {
                        // Display the delete icon based on the swipe direction
                        if (direction == DismissDirection.StartToEnd) {
                            Icon(
                                imageVector = Icons.Default.Delete, // Icon image
                                contentDescription = "Delete", // Content description for accessibility
                                tint = Color.White, // Icon color
                                modifier = Modifier
                                    .align(Alignment.CenterStart) // Align to the start
                                    .padding(start = 16.dp) // Padding at the start
                            )
                        } else if (direction == DismissDirection.EndToStart) {
                            Icon(
                                imageVector = Icons.Default.Delete, // Icon image
                                contentDescription = "Delete", // Content description for accessibility
                                tint = Color.White, // Icon color
                                modifier = Modifier
                                    .align(Alignment.CenterEnd) // Align to the end
                                    .padding(end = 16.dp) // Padding at the end
                            )
                        }
                    }
                }
            }
        },
        dismissContent = {
            // Content of the card that can be swiped to dismiss
            RequestItem(requestUi = request) // Display the request item
        }
    )
}
