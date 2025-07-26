package com.albayemre.expensetrackingapplication.app.domain.use_case

import com.albayemre.expensetrackingapplication.app.data.repository.ExpenseRepository
import com.albayemre.expensetrackingapplication.app.domain.model.Expense
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

/**
 * Use case for retrieving all [Expense] entries from the repository.
 *
 * This use case returns a [Flow] of a list of expenses, allowing the UI layer
 * to reactively observe changes in the data (e.g., inserts, deletes, updates).
 *
 * It is injected via constructor and invoked as a function using Kotlin's
 * operator overloading.
 */
class GetExpenses @Inject constructor(
    private val repository: ExpenseRepository
) {
    /**
     * Retrieves all expenses as a reactive [Flow].
     *
     * @return Flow<List<Expense>> that emits the current list and future updates.
     *
     * Usage:
     * ```
     * val expenses = getExpenses().collectAsState()
     * ```
     */
    operator fun invoke(): Flow<List<Expense>> {
        return repository.getExpenses()
    }
}
