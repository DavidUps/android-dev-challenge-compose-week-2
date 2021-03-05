package com.example.androiddevchallenge.features.countDown

import android.os.CountDownTimer
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class CountDownViewModel : ViewModel() {

    private val _time = MutableLiveData<Float>()
    val time: LiveData<Float> = _time

    fun startCountDown() {
        object : CountDownTimer(5000L, 1000) {
            override fun onTick(millisUntilFinished: Long) {
                _time.value = millisUntilFinished.toFloat() / 5000L
                Log.w("count", millisUntilFinished.toFloat().toString())
            }

            override fun onFinish() {
                _time.value = 0f
            }

        }.start()
    }
}