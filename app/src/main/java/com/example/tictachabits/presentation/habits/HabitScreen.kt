package com.example.tictachabits.presentation.habits

import android.content.Context
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.tictachabits.presentation.data.HabitDataStore
import com.example.tictachabits.presentation.habits.HabitList

@Composable
fun HabitScreen(habitDataStore: HabitDataStore) {
        HabitList(habitDataStore)

}
