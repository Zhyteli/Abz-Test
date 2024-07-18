package com.abztest.presentation.ui.item

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.core.updateTransition
import androidx.compose.animation.fadeOut
import androidx.compose.animation.shrinkVertically
import androidx.compose.material3.DismissValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.rememberDismissState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import com.abztest.presentation.model.RequestUi

@OptIn(ExperimentalMaterial3Api::class, ExperimentalAnimationApi::class)
@Composable
fun AnimatedSwipeToDeleteItem(
    request: RequestUi,
    onDelete: (RequestUi) -> Unit
) {
    var isVisible by remember { mutableStateOf(true) } // State to control the visibility of the item
    val dismissState = rememberDismissState(
        confirmValueChange = { dismissValue ->
            // Check if the item is dismissed either to the start or to the end
            if (dismissValue == DismissValue.DismissedToEnd || dismissValue == DismissValue.DismissedToStart) {
                isVisible = false // Set the visibility to false
                onDelete(request) // Call the onDelete function with the request
                true // Confirm the value change
            } else {
                false // Do not confirm the value change
            }
        }
    )

    // Create a transition for the visibility state
    val transition = updateTransition(targetState = isVisible, label = "SwipeToDeleteTransition")

    // Use AnimatedVisibility for handling the visibility transition
    transition.AnimatedVisibility(
        visible = { targetState -> targetState }, // Control visibility based on the target state
        exit = fadeOut() + shrinkVertically()     // Define the exit animations
    ) {
        // Composable that can be swiped to delete
        SwipeToDeleteItem(
            request = request,           // Pass the request
            dismissState = dismissState  // Pass the dismiss state
        )
    }
}
