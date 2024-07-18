package com.abztest.presentation.ui

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.abztest.presentation.model.RequestUi
import com.abztest.presentation.ui.error.ErrorDialog
import com.abztest.presentation.ui.item.AnimatedSwipeToDeleteItem
import com.abztest.presentation.viewmodel.ViewModelService
import kotlinx.coroutines.delay

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RequestListScreen(viewModelService: ViewModelService = viewModel()) {
    // Collecting state from the ViewModel
    val requests by viewModelService.requestList.collectAsState() // List of requests
    val error = viewModelService.errorVis.collectAsState().value // Error message
    val loading by viewModelService.loadingScreen.collectAsState() // Loading state
    var delete by remember {
        mutableStateOf(RequestUi(0, "", 1)) // State to hold the request to be deleted
    }

    // Launching a side-effect when 'delete' changes
    LaunchedEffect(key1 = delete) {
        delay(1000) // Delay to simulate some processing time
        if (delete != RequestUi(0, "", 1)) {
            viewModelService.deleteRequest(delete) // Delete the request using ViewModel
        }
    }

    // Display loading screen if loading is true
    if (loading) {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center // Center the content within the box
        ) {
            Text(
                text = "Empty, no requests yet.", // Display text when loading
                style = MaterialTheme.typography.headlineSmall.copy(
                    fontSize = 20.sp, // Set font size to 20sp
                    fontWeight = FontWeight.Bold // Set font weight to bold
                )
            )
        }
    } else {
        // Scaffold to provide basic material design structure
        Scaffold(
            topBar = {
                TopAppBar(title = { Text("Tracked Requests") }) // Top app bar with title
            },
            content = { padding ->
                // LazyColumn to display the list of requests
                LazyColumn(modifier = Modifier.padding(padding)) {
                    items(requests, key = { it.id }) { request ->
                        // Display each request with swipe-to-delete functionality
                        AnimatedSwipeToDeleteItem(
                            request = request,
                            onDelete = { delete = request } // Set the request to be deleted
                        )
                    }
                }
            }
        )
    }

    // Display error dialog if there is an error
    if (error != "") {
        ErrorDialog(message = error) // Show error dialog with the error message
    }
}
