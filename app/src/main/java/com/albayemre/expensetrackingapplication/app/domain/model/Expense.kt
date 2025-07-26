package com.albayemre.expensetrackingapplication.app.domain.model

import com.albayemre.expensetrackingapplication.app.data.local.ExpenseCategory
import java.time.LocalDate

/**
 * Domain-level model representing a single expense entry.
 *
 * This model is used across the application layers — including ViewModels, UseCases,
 * and UI Composables — as a clean representation of a user's financial transaction.
 *
 * ⚠️ This model is not tied to any database schema. Conversion is required
 * when reading/writing to the local database (i.e., mapping to Entity class).
 *
 * @property id Unique identifier for the expense (used by database/UI)
 * @property title Short description of the expense (e.g., "Groceries", "Gas")
 * @property amount Monetary value of the expense in Turkish Lira (₺)
 * @property category ExpenseCategory enum indicating the type of expense
 * @property date Date the expense was made (defaults to today)
 */
data class Expense(
    val id: Int = 0,
    val title: String,
    val amount: Double,
    val category: ExpenseCategory,
    val date: LocalDate = LocalDate.now()
)
