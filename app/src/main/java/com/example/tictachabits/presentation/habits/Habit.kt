package com.example.tictachabits.presentation.habits

data class Habit(
    val id: Int,
    val name: String,
    val iconResId: Int,
    var isCompleted: Boolean = false
)
