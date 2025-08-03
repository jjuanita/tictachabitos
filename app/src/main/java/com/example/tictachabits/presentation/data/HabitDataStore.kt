
package com.example.tictachabits.presentation.data

import android.content.Context
import androidx.datastore.preferences.core.*
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.flow.map

private val Context.dataStore by preferencesDataStore(name = "habit_preferences")

class HabitDataStore(private val context: Context) {
    // Keys dinámicas
    private fun habitKey(name: String) = booleanPreferencesKey(name)
    private fun habitCountKey(name: String) = intPreferencesKey("${name}_count")

    // Obtener estado (bool) de un hábito
    fun getHabitStatus(habitName: String): Flow<Boolean> {
        return context.dataStore.data.map { preferences ->
            preferences[habitKey(habitName)] ?: false
        }
    }

    // Establecer estado (bool)
    suspend fun setHabitStatus(habitName: String, status: Boolean) {
        context.dataStore.edit { preferences ->
            preferences[habitKey(habitName)] = status
        }
    }

    // Incrementar contador del hábito
    suspend fun incrementHabitCount(key: String) {
        val intKey = intPreferencesKey(key)
        context.dataStore.edit { prefs ->
            val current = prefs[intKey] ?: 0
            prefs[intKey] = current + 1
        }
    }

    // Obtener contador del hábito como Flow<Int>
    fun getHabitCount(key: String): Flow<Int> {
        val intKey = intPreferencesKey(key)
        return context.dataStore.data.map { preferences ->
            preferences[intKey] ?: 0
        }
    }

//se supone que es para el reinicio de habitos cada dia

private val LAST_RESET_KEY = stringPreferencesKey("last_reset_date")

suspend fun resetIfNewDay(habitNames: List<String>) {
    val today = java.time.LocalDate.now().toString()
    val lastReset = context.dataStore.data.firstOrNull()?.get(LAST_RESET_KEY)

    if (lastReset != today) {
        context.dataStore.edit { preferences ->
            habitNames.forEach { habit ->
                preferences[intPreferencesKey("${habit}_count")] = 0
                preferences[booleanPreferencesKey(habit)] = false
            }
            preferences[LAST_RESET_KEY] = today
        }
    }
}
}
