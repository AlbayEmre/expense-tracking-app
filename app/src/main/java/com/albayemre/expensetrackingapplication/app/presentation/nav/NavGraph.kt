package com.albayemre.expensetrackingapplication.app.presentation.nav

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.albayemre.expensetrackingapplication.app.presentation.ui.addedit.AddEditExpenseScreen
import com.albayemre.expensetrackingapplication.app.presentation.ui.list.ExpenseListScreen

/**
 * Contains route constants used for navigation between screens.
 */
object Routes {
    const val LIST = "list"         // Expense list screen
    const val ADD_EDIT = "add_edit" // Add/Edit expense screen
}

/**
 * Composable function defining the navigation graph for the app.
 *
 * @param navController Controller used to manage app navigation.
 * @param modifier Optional UI modifier passed to the NavHost.
 */
@Composable
fun ExpenseNavGraph(
    navController: NavHostController,
    modifier: Modifier = Modifier
) {
    NavHost(
        navController = navController,
        startDestination = Routes.LIST,
        modifier = modifier
    ) {

        /**
         * Expense list screen
         * Triggered on app start
         * Allows navigation to add/edit screen via FAB
         */
        composable(Routes.LIST) {
            ExpenseListScreen(
                onAddClick = {
                    navController.navigate(Routes.ADD_EDIT)
                }
            )
        }

        /**
         * Add/Edit expense screen
         * Triggered when FAB is pressed
         * Returns to list after saving
         */
        composable(Routes.ADD_EDIT) {
            AddEditExpenseScreen(
                onSaveComplete = {
                    navController.popBackStack()
                }
            )
        }
    }
}
