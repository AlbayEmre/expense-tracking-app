package com.albayemre.expensetrackingapplication.app.data.repository

import com.albayemre.expensetrackingapplication.app.domain.model.Expense
import kotlinx.coroutines.flow.Flow

/**
 * Interface that abstracts expense-related data operations.
 *
 * This repository separates the domain layer from the actual data source,
 * enabling flexibility and cleaner testable architecture.
 *
 * Can be implemented using:
 * - Room (local database)
 * - REST APIs (remote)
 * - In-memory cache (for testing)
 */
interface ExpenseRepository {

    /**
     * Returns a reactive stream of all [Expense] entries.
     *
     * @return [Flow] that emits the current and future lists of expenses.
     */
    fun getExpenses(): Flow<List<Expense>>

    /**
     * Inserts a new expense into the data source.
     *
     * @param expense The [Expense] model to insert.
     */
    suspend fun insert(expense: Expense)

    /**
     * Deletes the specified expense from the data source.
     *
     * @param expense The [Expense] to remove.
     */
    suspend fun delete(expense: Expense)

    /**
     * Returns the total sum of all expenses in Turkish Lira (₺).
     *
     * @return [Flow]<Double> representing the live total.
     */
    fun getTotal(): Flow<Double>

    /**
     * Returns the total amount spent, grouped by category.
     *
     * @return [Flow]<List<Pair<String, Double>>> — where the first element is the category name, and the second is the sum.
     */
    fun getTotalPerCategory(): Flow<List<Pair<String, Double>>>
}
