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
package com.example.androiddevchallenge.features.countDown

import android.os.CountDownTimer
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import java.util.concurrent.TimeUnit

class CountDownViewModel : ViewModel() {

    private val _time = MutableLiveData<String>()
    val time: LiveData<String> = _time

    private val _counterProgress = MutableLiveData<Float>()
    val counterProgress: LiveData<Float> = _counterProgress

    fun startCountDown(min: String, sec: String) {

        val minInMillis = TimeUnit.MINUTES.toMillis(min.toInt().toLong())
        val secInMillis = TimeUnit.SECONDS.toMillis(sec.toInt().toLong())
        val timeInMillis = minInMillis + secInMillis

        object : CountDownTimer(timeInMillis, 1000) {
            override fun onTick(millisUntilFinished: Long) {
                _counterProgress.value = millisUntilFinished.toFloat() / minInMillis
                val min = TimeUnit.MILLISECONDS.toSeconds(millisUntilFinished).toInt() / 60
                val sec = TimeUnit.MILLISECONDS.toSeconds(millisUntilFinished).toInt() % 60
                _time.value = "${min.toString().split(", ")[0]}:$sec"
            }

            override fun onFinish() {
                _counterProgress.value = 0f
            }
        }.start()
    }
}
