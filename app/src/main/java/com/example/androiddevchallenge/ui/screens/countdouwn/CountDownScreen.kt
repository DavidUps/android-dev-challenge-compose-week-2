/*
 * Copyright 2021 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.example.androiddevchallenge.ui.screens.countdouwn

import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.androiddevchallenge.features.countDown.CountDownViewModel
import com.example.androiddevchallenge.ui.screens.countdouwn.components.CountDown
import com.example.androiddevchallenge.ui.screens.countdouwn.components.SelectCountDown

@Composable
fun CountDownScreen(viewModel: CountDownViewModel = viewModel()) {

    val counterProgress by viewModel.counterProgress.observeAsState(1.0f)
    val time by viewModel.time.observeAsState("00:00")

    val context = LocalContext.current
    var minutes = ""
    var seconds = ""

    Column(modifier = Modifier.fillMaxSize()) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .weight(.20f),
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
                .weight(.80f),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            CountDown(time = time, counterProgress = counterProgress)
        }
        Row(
            modifier = Modifier
                .padding(bottom = 16.dp)
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            Button(
                onClick = {
                    when {
                        minutes.isNotEmpty() && seconds.isNotEmpty() -> startCountDown(
                            viewModel,
                            minutes,
                            seconds
                        )
                        minutes.isNotEmpty() -> startCountDown(viewModel, minutes, "0")
                        seconds.isNotEmpty() -> startCountDown(viewModel, "0", seconds)
                        else -> Toast.makeText(context, "fill all edit texts", Toast.LENGTH_SHORT)
                            .show()
                    }
                }
            ) {
                Text(text = "Start")
            }
        }
    }
}

fun startCountDown(viewModel: CountDownViewModel, minutes: String, seconds: String) {
    viewModel.startCountDown(min = minutes, sec = seconds)
}
