package com.abztest.presentation.ui.state

// Sealed class representing the state of the UI screen
sealed class StateScreenUI {

    // Object representing the loading state
    data object Loading : StateScreenUI()

    // Data class representing the error state, with an error message
    data class Error(val error: String) : StateScreenUI()
}
