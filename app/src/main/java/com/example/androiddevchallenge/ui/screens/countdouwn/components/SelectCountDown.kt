package com.example.androiddevchallenge.ui.screens.countdouwn.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp

@Composable
fun SelectCountDown(result: (String, String) -> Unit) {

    val (minutes, etMinutes) = remember {
        mutableStateOf("")
    }
    val (seconds, etSeconds) = remember {
        mutableStateOf("")
    }

    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center
    ) {
        OutlinedTextField(
            value = minutes,
            modifier = Modifier
                .padding(8.dp)
                .width(100.dp),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            label = { Text(text = "Minutes") },
            onValueChange = {
                etMinutes(it)
                result(it, seconds)
            }
        )

        OutlinedTextField(
            value = seconds,
            modifier = Modifier
                .padding(8.dp)
                .width(100.dp),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            label = { Text(text = "Seconds") },
            onValueChange = {
                etSeconds(it)
                result(etMinutes.toString(), etSeconds.toString())
            }
        )
    }

}