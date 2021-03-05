package com.example.androiddevchallenge.ui.screens.countdouwn

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.Button
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.androiddevchallenge.features.countDown.CountDownViewModel
import com.example.androiddevchallenge.ui.screens.countdouwn.components.CountDown
import com.example.androiddevchallenge.ui.screens.countdouwn.components.SelectCountDown
import com.example.androiddevchallenge.ui.theme.teal200

@Composable
fun CountDownScreen(viewModel: CountDownViewModel = viewModel()) {

    val time by viewModel.time.observeAsState(899977.0f)

    var minutes = ""
    var seconds = ""

    Column(modifier = Modifier.fillMaxSize()) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .weight(.30f),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            SelectCountDown(
                result = { min, sec ->
                    minutes = min
                    seconds = sec
                }
            )
        }
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .weight(.50f)
                .background(teal200),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            time?.let {
                CountDown(time = it)
            }
        }

        Row {
            Button(onClick = {
                viewModel.startCountDown() }
            ) {
                Text(text = "Start")
            }
        }
    }
}