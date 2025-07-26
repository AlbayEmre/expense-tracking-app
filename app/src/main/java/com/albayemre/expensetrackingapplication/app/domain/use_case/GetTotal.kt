package com.albayemre.expensetrackingapplication.app.domain.use_case

import com.albayemre.expensetrackingapplication.app.data.repository.ExpenseRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

/**
 * Use case for computing the total expense amount (in ₺) from all recorded expenses.
 *
 * This is a reactive use case that emits updated totals whenever the data changes.
 * It allows the ViewModel or UI layer to observe the total spending in real-time.
 */
class GetTotal @Inject constructor(
    private val repository: ExpenseRepository
) {

    /**
     * Returns a [Flow] of the current total expenses.
     *
     * Example:
     * - Initial state: ₺350
     * - Add new expense: ₺70
     * - Flow emits: ₺420 automatically
     *
     * @return [Flow]<Double> representing the total amount in Turkish Lira (₺).
     */
    operator fun invoke(): Flow<Double> {
        return repository.getTotal()
    }
}
