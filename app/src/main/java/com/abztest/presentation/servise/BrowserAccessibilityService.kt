package com.abztest.presentation.servise

import android.accessibilityservice.AccessibilityService
import android.content.Intent
import android.util.Log
import android.view.accessibility.AccessibilityEvent
import android.view.accessibility.AccessibilityNodeInfo
import android.widget.Toast
import com.abztest.R
import com.abztest.data.database.RequestEntity
import com.abztest.data.mapper.toDomain
import com.abztest.domain.usecase.InsertRequestUseCase
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.cancel
import kotlinx.coroutines.launch
import java.util.Calendar
import javax.inject.Inject

// Dagger-Hilt annotation to mark this class for dependency injection
@AndroidEntryPoint
class BrowserAccessibilityService : AccessibilityService() {

    @Inject
    lateinit var insertRequestUseCase: InsertRequestUseCase

    private var lastRequest: String? = null // Stores the last request text
    private var lastTimestamp: Long = 0     // Stores the timestamp of the last request
    private val scope = CoroutineScope(SupervisorJob() + Dispatchers.IO) // Coroutine scope for background operations

    // Called when an accessibility event occurs
    override fun onAccessibilityEvent(event: AccessibilityEvent?) {
        if (event?.eventType == AccessibilityEvent.TYPE_WINDOW_CONTENT_CHANGED) {
            event.source?.let { traverseNodeTree(it) } // Traverse the node tree to handle the event
        }
    }

    // Called when the accessibility service is interrupted
    override fun onInterrupt() {
        // Handle interrupt if necessary
    }

    // Called when the service is destroyed
    override fun onDestroy() {
        super.onDestroy()
        scope.cancel() // Cancel all coroutines
    }

    // Called when the service is connected
    override fun onServiceConnected() {
        super.onServiceConnected()
        Toast.makeText(this, getString(R.string.great), Toast.LENGTH_SHORT).show() // Show a toast message
    }

    // Traverse the node tree to find relevant nodes
    private fun traverseNodeTree(node: AccessibilityNodeInfo) {
        if (node.className == getString(R.string.edit) && node.viewIdResourceName != null) {
            val text = node.text?.toString() ?: return // Get the text from the node or return if null
            if (node.packageName == "com.android.chrome") {
                handleChromeRequest(text) // Handle request specific to Chrome
            } else {
                handleOtherRequest(text) // Handle requests from other packages
            }
        }
        // Recursively traverse child nodes
        for (i in 0 until node.childCount) {
            node.getChild(i)?.let { traverseNodeTree(it) }
        }
    }

    // Handle request logic specific to Chrome browser
    private fun handleChromeRequest(text: String) {
        when {
            text.contains(getString(R.string.search)) -> {
                parseUrl(text)?.takeIf { it.contains(getString(R.string.google_com)) }?.let { saveRequest(it) }
            }
            text.contains("${getString(R.string.google_com)}/") -> {
                saveRequest(extractDomain(text), text)
            }
        }
    }

    // Handle request logic for other applications
    private fun handleOtherRequest(text: String) {
        when {
            text.contains(getString(R.string.search)) -> {
                parseUrl(text)?.takeIf { it.contains(getString(R.string.google_com)) }?.let { saveRequest(it) }
            }
            text.contains("${getString(R.string.google_com)}/") -> {
                saveRequest(extractDomain(text), text)
            }
            text.isNotEmpty() -> {
                saveRequest(text)
            }
        }
    }

    // Extract the domain from a URL
    private fun extractDomain(url: String): String {
        return Regex("^(https?://)?([^/]+)").find(url)?.groupValues?.get(2) ?: ""
    }

    // Parse the URL to extract the query
    private fun parseUrl(url: String): String? {
        val queryStartIndex = url.indexOf("q=")
        val clientStartIndex = url.indexOf("&")
        return if (queryStartIndex != -1 && clientStartIndex != -1) {
            url.substring(queryStartIndex + 2, clientStartIndex).replace("+", " ")
        } else {
            null
        }
    }

    // Save the request to the database with only the query
    private fun saveRequest(query: String, url: String? = null) {
        val timestamp = Calendar.getInstance().timeInMillis

        // Check if the request is the same as the last one or if the last request was too recent
        if (query.isBlank() || (lastRequest == query || (timestamp - lastTimestamp) < 1000)) {
            lastTimestamp = timestamp
            return
        }
        lastRequest = query
        lastTimestamp = timestamp

        val request = RequestEntity(request = query, timestamp = timestamp, url = url.toString())

        scope.launch {
            insertRequestUseCase(request.toDomain()) // Insert the request into the database
            // Notify ViewModelService to update the list
            sendBroadcast(Intent("com.example.UPDATE_REQUEST_LIST"))
        }
    }
}
