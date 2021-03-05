package com.example.androiddevchallenge.features.countDown

import android.os.CountDownTimer
import android.util.Log
import android.util.TimeUtils
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