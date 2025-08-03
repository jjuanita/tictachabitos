package com.example.tictachabits.presentation.habits


import android.content.Context
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.clickable
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.wear.compose.material.*

import com.example.tictachabits.R
import com.example.tictachabits.presentation.data.HabitDataStore
import kotlinx.coroutines.launch
import java.time.LocalDate

@Composable
fun HabitList(habitDataStore: HabitDataStore) {
    val icons = listOf(
        R.drawable.ic_water,
        R.drawable.ic_walk,
        R.drawable.ic_meditate,
        R.drawable.ic_food,
        R.drawable.ic_nophone
    )

    val habitNames = listOf(
        "Beber Agua",
        "Caminar",
        "Meditar",
        "Comer Saludable",
        "Desconectarse"
    )

    val coroutineScope = rememberCoroutineScope()
    val today = remember { LocalDate.now().toString() }  // Para usar como key

    Scaffold(
        modifier = Modifier.background(Color.Black),
        timeText = { TimeText() },
        vignette = { Vignette(vignettePosition = VignettePosition.TopAndBottom) }
    ) {
        ScalingLazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 8.dp),
            contentPadding = PaddingValues(vertical = 8.dp)
        ) {
            items(icons.size) { index ->
                val habitName = habitNames[index]
                val countKey = "${habitName}_$today" // contador por día
                val countFlow = remember(habitName) {
                    habitDataStore.getHabitCount(countKey)
                }
                val count by countFlow.collectAsState(initial = 0)

                val colors = listOf(
                    Color(0xFFE0E0E0), // 0
                    Color(0xFFBBDEFB), // 1
                    Color(0xFF64B5F6), // 2
                    Color(0xFF42A5F5), // 3
                    Color(0xFF2196F3), // 4
                    Color(0xFF1976D2)  // 5
                )

                val currentColor = colors.getOrElse(count.coerceAtMost(5)) { colors.last() }

                Card(
                    onClick = {
                        if (count < 5) {
                            coroutineScope.launch {
                                habitDataStore.incrementHabitCount(countKey)
                            }
                        }
                    },
                    backgroundPainter = CardDefaults.cardBackgroundPainter(currentColor),
                    modifier = Modifier
                        .padding(vertical = 6.dp)
                        .fillMaxWidth()
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(8.dp),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Image(
                            painter = painterResource(id = icons[index]),
                            contentDescription = habitName,
                            modifier = Modifier.size(36.dp)
                        )
                        Spacer(modifier = Modifier.height(4.dp))
                        Text(
                            text = "$habitName ",
                            color = Color.Black,
                            fontSize = MaterialTheme.typography.body2.fontSize,
                            textAlign = androidx.compose.ui.text.style.TextAlign.Center
                        )
                    }
                }
            }
        }
    }
}
