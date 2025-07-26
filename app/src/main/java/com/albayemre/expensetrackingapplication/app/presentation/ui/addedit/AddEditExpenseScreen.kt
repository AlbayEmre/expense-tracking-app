package com.albayemre.expensetrackingapplication.app.presentation.ui.addedit

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.albayemre.expensetrackingapplication.app.data.local.ExpenseCategory
import com.albayemre.expensetrackingapplication.app.presentation.viewmodel.ExpenseViewModel

/**
 * Composable screen for adding or editing an expense entry.
 *
 * @param onSaveComplete Callback executed after saving (e.g., navigate back)
 * @param viewModel ViewModel injected via Hilt that handles add logic
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddEditExpenseScreen(
    onSaveComplete: () -> Unit,
    viewModel: ExpenseViewModel = hiltViewModel()
) {
    var title by remember { mutableStateOf("") }
    var amountText by remember { mutableStateOf("") }
    var category by remember { mutableStateOf(ExpenseCategory.FOOD) }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Yeni Harcama") },
                actions = {
                    IconButton(onClick = {
                        val amount = amountText.toDoubleOrNull() ?: 0.0
                        viewModel.add(title, amount, category)
                        onSaveComplete()
                    }) {
                        Icon(Icons.Default.Check, contentDescription = "Kaydet")
                    }
                }
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .padding(paddingValues)
                .padding(16.dp)
        ) {
            OutlinedTextField(
                value = title,
                onValueChange = { title = it },
                label = { Text("Başlık") },
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(Modifier.height(8.dp))

            OutlinedTextField(
                value = amountText,
                onValueChange = { amountText = it },
                label = { Text("Tutar") },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(Modifier.height(8.dp))

            CategoryDropdown(
                selected = category,
                onSelectedChange = { category = it }
            )
        }
    }
}

/**
 * Dropdown component for selecting an expense category.
 *
 * @param selected Currently selected category
 * @param onSelectedChange Callback triggered when a new category is selected
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun CategoryDropdown(
    selected: ExpenseCategory,
    onSelectedChange: (ExpenseCategory) -> Unit
) {
    var expanded by remember { mutableStateOf(false) }

    ExposedDropdownMenuBox(
        expanded = expanded,
        onExpandedChange = { expanded = !expanded }
    ) {
        TextField(
            value = selected.name,
            onValueChange = {},
            readOnly = true,
            label = { Text("Kategori") },
            trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded) },
            modifier = Modifier.menuAnchor()
        )

        ExposedDropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false }
        ) {
            ExpenseCategory.values().forEach { cat ->
                DropdownMenuItem(
                    text = { Text(cat.name) },
                    onClick = {
                        onSelectedChange(cat)
                        expanded = false
                    }
                )
            }
        }
    }
}
