package com.albayemre.expensetrackingapplication.app.data.local

import androidx.room.*
import kotlinx.coroutines.flow.Flow

/**
 * Room DAO (Data Access Object) interface for handling all expense-related database operations.
 *
 * It provides:
 * - Insertion and deletion of expenses
 * - Reactive retrieval of expense data (as [Flow])
 * - Aggregated queries such as total expenses and category breakdowns
 */
@Dao
interface ExpenseDao {

    /**
     * Retrieves all expense records sorted by descending date (most recent first).
     *
     * @return [Flow] emitting a list of [ExpenseEntity] items
     */
    @Query("SELECT * FROM expense ORDER BY date DESC")
    fun getExpenses(): Flow<List<ExpenseEntity>>

    /**
     * Inserts or updates a single expense record in the database.
     *
     * @param expense The [ExpenseEntity] to insert or replace
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(expense: ExpenseEntity)

    /**
     * Deletes a specific expense entry from the database.
     *
     * @param expense The [ExpenseEntity] to delete
     */
    @Delete
    suspend fun delete(expense: ExpenseEntity)

    /**
     * Calculates the total amount of all expenses stored.
     *
     * @return [Flow] emitting the total as [Double], or null if no records exist
     */
    @Query("SELECT SUM(amount) FROM expense")
    fun getTotal(): Flow<Double?>

    /**
     * Returns a grouped summary of expenses by category.
     * Each result contains a category and its associated total spending.
     *
     * Used for visualizations or analytics.
     *
     * @return [Flow] emitting a list of [CategoryTotal] DTOs
     */
    @Query("SELECT category, SUM(amount) as total FROM expense GROUP BY category")
    fun getTotalPerCategory(): Flow<List<CategoryTotal>>
}
