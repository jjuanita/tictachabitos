package com.example.tictachabits.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.tictachabits.presentation.alarm.AlarmHelper
import com.example.tictachabits.presentation.data.HabitDataStore
import com.example.tictachabits.presentation.habits.HabitList
import com.example.tictachabits.presentation.welcome.WelcomeScreen
import com.example.tictachabits.presentation.habits.HabitScreen
import com.example.tictachabits.presentation.habits.HabitStatsScreen
import com.example.tictachabits.presentation.theme.TicTacHabitsTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        val habitDataStore = HabitDataStore(applicationContext)

        setContent {
            TicTacHabitsTheme {
                AppNavigation(habitDataStore = habitDataStore)
            }
        }
    }

    @Composable
    fun AppNavigation(habitDataStore: HabitDataStore) {
        val navController: NavHostController = rememberNavController()
        val context = LocalContext.current

        NavHost(navController = navController, startDestination = "welcome") {
            composable("welcome") {
                WelcomeScreen(
                    onStartHabits = {
                    AlarmHelper.setOneTimeReminder (context)
                     navController.navigate("habits") },
                    onViewStats = { navController.navigate("habitStats")}
                )
            }

            composable("habits") {
                HabitScreen(habitDataStore = habitDataStore)
            }

            composable("habitStats") {
                HabitStatsScreen(habitDataStore = habitDataStore)
            }


        }
    }
}
