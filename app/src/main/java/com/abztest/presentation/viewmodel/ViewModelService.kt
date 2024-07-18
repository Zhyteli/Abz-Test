package com.abztest.presentation.viewmodel

import android.app.Application
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.os.Build
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.abztest.data.mapper.toDomain
import com.abztest.presentation.mapper.toDomain
import com.abztest.domain.usecase.DeleteRequestUseCase
import com.abztest.domain.usecase.GetFilteredRequestsUseCase
import com.abztest.domain.usecase.InsertRequestUseCase
import com.abztest.presentation.mapper.toUI
import com.abztest.presentation.model.RequestUi
import com.abztest.presentation.ui.state.StateScreenUI
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ViewModelService @Inject constructor(
    private val application: Application,
    private val getFilteredRequestsUseCase: GetFilteredRequestsUseCase,
    private val deleteRequestUseCase: DeleteRequestUseCase
) : AndroidViewModel(application) {

    private val _requestList = MutableStateFlow<List<RequestUi>>(emptyList()) // Mutable state flow for the list of requests
    val requestList: StateFlow<List<RequestUi>> get() = _requestList // Public state flow for the list of requests

    private val _loadingScreen = MutableStateFlow(true) // Mutable state flow for the loading state
    val loadingScreen: StateFlow<Boolean> get() = _loadingScreen // Public state flow for the loading state

    private val _errorVis = MutableStateFlow("") // Mutable state flow for the error message
    val errorVis: StateFlow<String> get() = _errorVis // Public state flow for the error message

    private val state = MutableStateFlow<StateScreenUI>(StateScreenUI.Loading) // Mutable state flow for the UI state

    // BroadcastReceiver to listen for updates
    private val updateReceiver = object : BroadcastReceiver() {
        override fun onReceive(context: Context?, intent: Intent?) {
            if (intent?.action == "com.example.UPDATE_REQUEST_LIST") {
                getRequests() // Fetch requests when the broadcast is received
            }
        }
    }

    // Initializer block
    init {
        try {
            // Register the BroadcastReceiver to listen for updates
            val intentFilter = IntentFilter("com.example.UPDATE_REQUEST_LIST")
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                application.registerReceiver(
                    updateReceiver,
                    intentFilter,
                    Context.RECEIVER_NOT_EXPORTED
                )
            } else {
                application.registerReceiver(updateReceiver, intentFilter, Context.RECEIVER_EXPORTED)
            }
        } catch (e: Exception) {
            state.value = StateScreenUI.Error(e.message.toString()) // Set the state to Error if an exception occurs
        }
        startState() // Start the state management
    }

    // Function to start managing the state
    private fun startState() {
        viewModelScope.launch {
            state.collect { state ->
                when (state) {
                    StateScreenUI.Loading -> {
                        getRequests() // Fetch requests when the state is Loading
                    }
                    is StateScreenUI.Error -> {
                        // Handle error state
                        _errorVis.value = state.error // Set the error message
                    }
                }
            }
        }
    }

    // Function to fetch requests
    private fun getRequests() {
        viewModelScope.launch {
            val requests = getFilteredRequestsUseCase("%google.com%").firstOrNull().orEmpty() // Fetch requests containing "google.com"
            if (requests.isNotEmpty()) {
                _requestList.value = requests.map { it.toUI() }
                    .sortedByDescending { it.timestamp } // Update the request list and sort by timestamp
                _loadingScreen.value = false // Set loading to false
            }
        }
    }

    // Function to delete a request
    fun deleteRequest(request: RequestUi) {
        viewModelScope.launch {
            deleteRequestUseCase(request.toDomain()) // Delete the request
            _requestList.value = _requestList.value.filter { it.id != request.id }
                .sortedByDescending { it.timestamp } // Update the request list
        }
    }

    // Cleanup function called when the ViewModel is cleared
    override fun onCleared() {
        super.onCleared()
        application.unregisterReceiver(updateReceiver) // Unregister the BroadcastReceiver
    }
}
