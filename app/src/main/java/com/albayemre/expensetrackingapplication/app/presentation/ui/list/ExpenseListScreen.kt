package com.albayemre.expensetrackingapplication.app.presentation.ui.list

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.albayemre.expensetrackingapplication.app.domain.model.Expense
import com.albayemre.expensetrackingapplication.app.presentation.viewmodel.ExpenseViewModel

/**
 * Main screen to display the list of expenses and total spending.
 *
 * @param onAddClick Callback triggered when the FAB (+) is clicked.
 * @param viewModel Provided via Hilt (default), exposes expenses and total state.
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ExpenseListScreen(
    onAddClick: () -> Unit,
    viewModel: ExpenseViewModel = hiltViewModel()
) {
    val expenses by viewModel.expenses.collectAsState()
    val total by viewModel.total.collectAsState()

    Scaffold(
        topBar = {
            TopAppBar(title = { Text("Harcama Listesi") })
        },
        floatingActionButton = {
            FloatingActionButton(onClick = onAddClick) {
                Icon(Icons.Default.Add, contentDescription = "Yeni Harcama Ekle")
            }
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .padding(padding)
                .fillMaxSize()
        ) {
            TotalCard(total = total)

            LazyColumn(modifier = Modifier.weight(1f)) {
                items(expenses, key = { it.id }) { expense ->
                    ExpenseRow(
                        expense = expense,
                        onDelete = { viewModel.delete(expense) }
                    )
                    Divider(thickness = 5.dp, color = Color.Gray)
                }
            }
        }
    }
}

/**
 * Displays a card showing the user's total expenses.
 *
 * @param total The total spending amount in Turkish Lira.
 */
@Composable
private fun TotalCard(total: Double) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        shape = RoundedCornerShape(15.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.primaryContainer
        )
    ) {
        Text(
            text = "Toplam Harcama: ₺${"%.2f".format(total)}",
            style = MaterialTheme.typography.titleMedium,
            modifier = Modifier.padding(16.dp)
        )
    }
}

/**
 * Renders a single expense row with title, category, amount, and delete button.
 *
 * @param expense The expense to be displayed.
 * @param onDelete Callback triggered when delete icon is clicked.
 */
@Composable
private fun ExpenseRow(expense: Expense, onDelete: () -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Column {
            Text(
                text = expense.title,
                style = MaterialTheme.typography.bodyLarge
            )
            Text(
                text = expense.category.name,
                style = MaterialTheme.typography.labelMedium
            )
        }

        Row(verticalAlignment = Alignment.CenterVertically) {
            Text("₺${"%.2f".format(expense.amount)}")
            IconButton(onClick = onDelete) {
                Icon(Icons.Default.Delete, contentDescription = "Sil")
            }
        }
    }
}
