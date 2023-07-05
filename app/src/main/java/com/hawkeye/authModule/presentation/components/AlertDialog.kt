package com.hawkeye.authModule.presentation.components

import androidx.compose.material.AlertDialog
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember

@Composable
fun AlertDialog() {
    val openDialog = remember { mutableStateOf(false) }

    Button(onClick = { openDialog.value = true }) {
        Text(text = "Connect")
    }
    if (openDialog.value) {
        AlertDialog(
            onDismissRequest = { openDialog.value = false },
            title = {
                Text(text = "Alert")
            },
            text = {
                Text(text = "Do you want?")
            },
            confirmButton = {
                Button(
                    onClick = { openDialog.value = false }
                ) {
                    Text(text = "Yes")
                }
            },
            dismissButton = {
                Button(
                    onClick = { openDialog.value = false }
                ) {
                    Text(text = "No")
                }
            },
        )
    }
}