package com.example.then_ny.core.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.example.then_ny.add_note.presentation.AddNoteScreen
import com.example.then_ny.core.presentation.ui.theme.TheNNYTheme
import com.example.then_ny.note_details.presentation.NoteDetailsScreen
import com.example.then_ny.note_list.presentation.NoteListScreen
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            TheNNYTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Navigation(modifier = Modifier.padding(innerPadding))
                }
            }
        }
    }
}

@Composable
fun Navigation(modifier: Modifier = Modifier) {
    val navController = rememberNavController()

    NavHost(
        modifier = modifier,
        navController = navController,
        startDestination = Screen.NoteListScreen
    ) {
        composable<Screen.NoteListScreen> {
            NoteListScreen(
                onNavigateToAddNote = { navController.navigate(Screen.AddNoteScreen) },
                onNavigateToNoteDetail = { navController.navigate(Screen.NoteDetailsScreen(id = it)) }
            )
        }

        composable<Screen.AddNoteScreen> {
            AddNoteScreen(onSave = {navController.popBackStack()})
        }

        composable<Screen.NoteDetailsScreen> { backStackEntry ->
            val noteDetailsScreen = backStackEntry.toRoute<Screen.NoteDetailsScreen>()
            NoteDetailsScreen(
                id = noteDetailsScreen.id,
                onSave = { navController.popBackStack() }
            )
        }

    }
}

