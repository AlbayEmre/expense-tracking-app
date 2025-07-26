package com.albayemre.expensetrackingapplication.app.domain.use_case

import com.albayemre.expensetrackingapplication.app.data.repository.ExpenseRepository
import com.albayemre.expensetrackingapplication.app.domain.model.Expense
import javax.inject.Inject

/**
 * Use case for deleting an [Expense] from the repository.
 *
 * This class encapsulates the business rule of removing an existing expense
 * from the application's persistent storage. It ensures that the ViewModel
 * layer interacts only with the domain layer, not directly with data sources.
 *
 * Usage:
 * ```
 * viewModelScope.launch {
 *     deleteExpense(expense)
 * }
 * ```
 */
class DeleteExpense @Inject constructor(
    private val repository: ExpenseRepository
) {
    /**
     * Executes the delete operation for the given [expense].
     *
     * @param expense The expense item to be deleted from the database.
     */
    suspend operator fun invoke(expense: Expense) {
        repository.delete(expense)
    }
}
