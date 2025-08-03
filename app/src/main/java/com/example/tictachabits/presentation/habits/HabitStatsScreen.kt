package com.example.tictachabits.presentation.habits

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.wear.compose.material.*
import com.example.tictachabits.presentation.data.HabitDataStore
import kotlinx.coroutines.flow.firstOrNull
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import androidx.compose.ui.Alignment
import androidx.compose.ui.draw.clip
import androidx.compose.foundation.shape.RoundedCornerShape
import java.time.LocalDate

@Composable
fun HabitStatsScreen(habitDataStore: HabitDataStore) {
    val habitList = listOf("Beber Agua", "Caminar", "Meditar", "Comer Saludable", "Desconectarse")
    val habitCounts = remember { mutableStateListOf<Int>() }
    val isLoading = remember { mutableStateOf(true) }

    val today = remember { LocalDate.now().toString() }

    LaunchedEffect(Unit) {
        habitDataStore.resetIfNewDay(habitList)

        habitCounts.clear()
        for (habit in habitList) {
            val habitKey = "${habit}_$today"
            val count = habitDataStore.getHabitCount(habitKey).firstOrNull() ?: 0
            habitCounts.add(count)
        }
        isLoading.value = false
    }

    if (isLoading.value) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.Black),
            contentAlignment = Alignment.Center
        ) {
            CircularProgressIndicator()
        }
    } else {
        ScalingLazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.Black),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            item {
                Text(
                    text = "Progreso diario",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.White
                )
                Spacer(modifier = Modifier.height(8.dp))
            }

            habitList.forEachIndexed { index, habit ->
                val count = habitCounts.getOrNull(index) ?: 0
                val progress = (count.coerceAtMost(10)) / 10f

                item {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 6.dp, horizontal = 10.dp)
                    ) {
                        Text(
                            text = "$habit: $count veces",
                            color = Color.White,
                            fontSize = 12.sp
                        )
                        Spacer(modifier = Modifier.height(4.dp))
                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(6.dp)
                                .clip(RoundedCornerShape(50))
                                .background(Color.Gray)
                        ) {
                            Box(
                                modifier = Modifier
                                    .fillMaxHeight()
                                    .fillMaxWidth(fraction = progress)
                                    .clip(RoundedCornerShape(50))
                                    .background(Color(0xFF4CAF50))
                            )
                        }
                    }
                }
            }
        }
    }
}
