package com.albayemre.expensetrackingapplication.app.domain.use_case

import com.albayemre.expensetrackingapplication.app.data.repository.ExpenseRepository
import com.albayemre.expensetrackingapplication.app.domain.model.Expense
import javax.inject.Inject

/**
 * Use case for adding a new [Expense] into the repository.
 *
 * This use case follows the clean architecture principle where business logic
 * is separated from UI and data layers.
 *
 * Injected via constructor using Hilt, and called using the 'invoke' operator.
 */
class AddExpense @Inject constructor(
    private val repository: ExpenseRepository
) {
    /**
     * Adds the given [expense] to the underlying data source (e.g., Room DB).
     *
     * Usage:
     * ```
     * viewModelScope.launch {
     *     addExpense(Expense(...))
     * }
     * ```
     *
     * @param expense The [Expense] model to be persisted.
     */
    suspend operator fun invoke(expense: Expense) {
        repository.insert(expense)
    }
}
