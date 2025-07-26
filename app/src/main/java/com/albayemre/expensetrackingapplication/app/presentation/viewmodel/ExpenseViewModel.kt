package com.albayemre.expensetrackingapplication.app.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.albayemre.expensetrackingapplication.app.data.local.ExpenseCategory
import com.albayemre.expensetrackingapplication.app.domain.model.Expense
import com.albayemre.expensetrackingapplication.app.domain.use_case.*
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import java.time.LocalDate
import javax.inject.Inject

/**
 * ViewModel for managing expense-related UI state and user actions.
 *
 * It exposes state flows for:
 * - All expenses
 * - Total expense
 * - Per-category totals
 *
 * Also provides methods for:
 * - Adding a new expense
 * - Deleting an existing expense
 *
 * Uses Hilt for constructor injection of domain use cases.
 */
@HiltViewModel
class ExpenseViewModel @Inject constructor(
    getExpenses: GetExpenses,
    private val addExpense: AddExpense,
    private val deleteExpense: DeleteExpense,
    getTotal: GetTotal,
    getTotalPerCategory: GetTotalPerCategory
) : ViewModel() {

    /**
     * Flow representing the list of all expenses.
     * Automatically updated via reactive streams.
     */
    val expenses = getExpenses().stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5_000),
        initialValue = emptyList()
    )

    /**
     * Flow representing the total amount of all expenses.
     */
    val total = getTotal().stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5_000),
        initialValue = 0.0
    )

    /**
     * Flow representing total spending per category.
     * Useful for charting or analytics.
     */
    val categoryTotals = getTotalPerCategory().stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5_000),
        initialValue = emptyList()
    )

    /**
     * Adds a new expense with the current date.
     *
     * @param title Title or description of the expense
     * @param amount Monetary value of the expense
     * @param category Category of the expense
     */
    fun add(title: String, amount: Double, category: ExpenseCategory) {
        viewModelScope.launch {
            addExpense(
                Expense(
                    title = title,
                    amount = amount,
                    category = category,
                    date = LocalDate.now()
                )
            )
        }
    }

    /**
     * Deletes the given expense from the data source.
     *
     * @param expense Expense to be removed
     */
    fun delete(expense: Expense) {
        viewModelScope.launch {
            deleteExpense(expense)
        }
    }
}
