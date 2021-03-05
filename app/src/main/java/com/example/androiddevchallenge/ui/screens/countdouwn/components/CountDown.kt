package com.example.androiddevchallenge.ui.screens.countdouwn.components

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.LinearProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.ProgressIndicatorDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.dp
import com.example.androiddevchallenge.ui.theme.black

@Composable
fun CountDown(time: Float) {

    val animatedProgress by animateFloatAsState(
        targetValue = time,
        animationSpec = ProgressIndicatorDefaults.ProgressAnimationSpec
    )

    Box {
        CircularProgressIndicatorBackGround(
            modifier = Modifier
                .height(250.dp)
                .width(250.dp),
            black,
            2
        )

        CircularProgressIndicator(
            progress = animatedProgress,
            modifier = Modifier
                .height(250.dp)
                .width(250.dp),
            color = MaterialTheme.colors.primary,
            strokeWidth = 2.dp,
        )
    }
}

@Composable
fun CircularProgressIndicatorBackGround(
    modifier: Modifier = Modifier,
    color: Color,
    stroke: Int
) {
    val style = with(LocalDensity.current) { Stroke(stroke.dp.toPx()) }
    Canvas(modifier) {
        val innerRadius = (size.minDimension - style.width) / 2
        drawArc(
            color = color,
            startAngle = 0f,
            sweepAngle = 360f,
            topLeft = Offset(
                (size / 2.0f).width - innerRadius,
                (size / 2.0f).height - innerRadius
            ),
            size = Size(innerRadius * 2, innerRadius * 2),
            useCenter = false,
            style = style
        )
    }
}