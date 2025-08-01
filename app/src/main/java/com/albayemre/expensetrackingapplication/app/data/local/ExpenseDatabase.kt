package com.albayemre.expensetrackingapplication.app.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.albayemre.expensetrackingapplication.app.data.local.converter.Converters

/**
 * Primary Room database class for the Expense Tracking Application.
 *
 * Responsible for:
 * - Defining entities (tables) used in the database
 * - Registering DAO interfaces for data access
 * - Providing type converters for unsupported data types (e.g., LocalDate, Enums)
 *
 * @see ExpenseEntity
 * @see ExpenseDao
 * @see Converters
 */
@Database(
    entities = [ExpenseEntity::class], // List of all tables in the database
    version = 1,                       // Increment this on schema changes
    exportSchema = false              // Disable schema export for simplicity (set true in production)
)
@TypeConverters(Converters::class)    // Registers custom converters for unsupported Room types
abstract class ExpenseDatabase : RoomDatabase() {

    /**
     * Provides access to Expense-related queries via the DAO interface.
     *
     * @return [ExpenseDao] implementation auto-generated by Room.
     */
    abstract val expenseDao: ExpenseDao
}
