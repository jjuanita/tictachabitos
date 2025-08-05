package com.example.tictachabits.presentation 

import androidx.compose.animation.core.animateFloatAsState //este archivo solo se creo para probar este import
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.foundation.layout.*
import androidx.compose.ui.unit.dp
import androidx.wear.compose.material.Text

@Composable
fun TestAnimation() {
    var progress by remember { mutableStateOf(0.5f) }
    val animatedProgress by animateFloatAsState(targetValue = progress)

    Column(modifier = Modifier.padding(16.dp)) {
        Text("Progreso: ${animatedProgress}")
    }
}
