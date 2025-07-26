package com.albayemre.expensetrackingapplication.app.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.time.LocalDate

/**
 * Room Entity representing a row in the "expense" table.
 *
 * This data class defines the schema used for storing expense records locally.
 * It should be mapped to/from the domain model [Expense] before being used
 * outside the data layer.
 *
 * @property id Auto-generated primary key for the expense entry
 * @property title Short description of the expense (e.g., "Groceries")
 * @property amount Expense amount in Turkish Lira (₺)
 * @property category ExpenseCategory enum indicating the type
 * @property date Date the expense occurred; defaults to today
 */
@Entity(tableName = "expense")
data class ExpenseEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,

    val title: String,
    val amount: Double,
    val category: ExpenseCategory,
    val date: LocalDate = LocalDate.now()
)
