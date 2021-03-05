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
package com.example.androiddevchallenge.ui.screens.countdouwn.components

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.CircularProgressIndicator
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
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.example.androiddevchallenge.ui.theme.black

@Composable
fun CountDown(time: String, counterProgress: Float) {

    val animatedProgress by animateFloatAsState(
        targetValue = counterProgress,
        animationSpec = ProgressIndicatorDefaults.ProgressAnimationSpec
    )

    Box {
        CircularProgressIndicatorBackGround(
            modifier = Modifier
                .height(250.dp)
                .width(250.dp),
            black,
            9.dp
        )

        CircularProgressIndicator(
            progress = animatedProgress,
            modifier = Modifier
                .height(250.dp)
                .width(250.dp),
            color = MaterialTheme.colors.primary,
            strokeWidth = 9.dp,
        )
    }
    Text(
        text = time,
        style = MaterialTheme.typography.h3,
        modifier = Modifier.padding(start = 5.dp, top = 10.dp)
    )
}

@Composable
fun CircularProgressIndicatorBackGround(
    modifier: Modifier = Modifier,
    color: Color,
    stroke: Dp
) {
    val style = with(LocalDensity.current) { Stroke(stroke.toPx()) }
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
