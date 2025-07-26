package com.albayemre.expensetrackingapplication

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.navigation.compose.rememberNavController
import com.albayemre.expensetrackingapplication.app.presentation.nav.ExpenseNavGraph
import com.albayemre.expensetrackingapplication.ui.theme.ExpenseTrackingApplicationTheme
import dagger.hilt.android.AndroidEntryPoint

/**
 * MainActivity serves as the root of the application.
 * It sets up Jetpack Compose, initializes the navigation graph,
 * and applies the global MaterialTheme using Compose.
 *
 * Annotated with @AndroidEntryPoint to enable Hilt DI (e.g., injecting ViewModels).
 */
@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    /**
     * Called when the activity is first created.
     * Responsible for initializing the UI content using Compose.
     *
     * @param savedInstanceState The saved state of the activity if it was previously destroyed.
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Set the UI content using Jetpack Compose
        setContent {
            // Apply the application-wide theme
            ExpenseTrackingApplicationTheme {

                // Create a surface with background color defined by the theme
                Surface(color = MaterialTheme.colorScheme.background) {

                    // Initialize NavController to handle screen navigation
                    val navController = rememberNavController()

                    // Start the navigation graph for controlling screen transitions
                    ExpenseNavGraph(navController = navController)
                }
            }
        }
    }
}
