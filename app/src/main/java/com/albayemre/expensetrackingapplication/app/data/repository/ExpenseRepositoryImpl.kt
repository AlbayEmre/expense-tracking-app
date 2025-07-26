package com.albayemre.expensetrackingapplication.app.data.repository

import com.albayemre.expensetrackingapplication.app.data.local.ExpenseDao
import com.albayemre.expensetrackingapplication.app.data.local.ExpenseEntity
import com.albayemre.expensetrackingapplication.app.domain.model.Expense
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

/**
 * Concrete implementation of [ExpenseRepository] that uses Room (via [ExpenseDao])
 * to perform local data operations. Maps between Room entities and domain models.
 */
class ExpenseRepositoryImpl @Inject constructor(
    private val dao: ExpenseDao
) : ExpenseRepository {

    // Room Entity → Domain Model mapper
    private fun ExpenseEntity.toDomain(): Expense =
        Expense(id, title, amount, category, date)

    /**
     * Retrieves all expenses from the database as a reactive [Flow].
     * Automatically maps Room entities to domain models.
     */
    override fun getExpenses(): Flow<List<Expense>> =
        dao.getExpenses().map { entityList ->
            entityList.map { it.toDomain() }
        }

    /**
     * Inserts a new expense into the database.
     * Domain model is mapped to Room entity before persistence.
     */
    override suspend fun insert(expense: Expense) {
        dao.insert(
            ExpenseEntity(
                id = expense.id,
                title = expense.title,
                amount = expense.amount,
                category = expense.category,
                date = expense.date
            )
        )
    }

    /**
     * Deletes an existing expense from the database.
     * Requires mapping from domain to Room entity.
     */
    override suspend fun delete(expense: Expense) {
        dao.delete(
            ExpenseEntity(
                id = expense.id,
                title = expense.title,
                amount = expense.amount,
                category = expense.category,
                date = expense.date
            )
        )
    }

    /**
     * Computes the total of all expenses.
     * Handles null results by converting them to 0.0.
     */
    override fun getTotal(): Flow<Double> =
        dao.getTotal().map { it ?: 0.0 }

    /**
     * Retrieves total expense amounts grouped by category.
     * Converts internal Room result to [Pair<String, Double>] for domain usage.
     */
    override fun getTotalPerCategory(): Flow<List<Pair<String, Double>>> =
        dao.getTotalPerCategory().map { categoryTotals ->
            categoryTotals.map { it.category.name to it.total }
        }
}
