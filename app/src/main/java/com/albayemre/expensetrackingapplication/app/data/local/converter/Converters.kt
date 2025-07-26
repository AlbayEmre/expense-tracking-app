package com.albayemre.expensetrackingapplication.app.data.local.converter

import androidx.room.TypeConverter
import com.albayemre.expensetrackingapplication.app.data.local.ExpenseCategory
import java.time.LocalDate

/**
 * Type converter class used by Room to handle non-primitive data types.
 *
 * Converts:
 * - [ExpenseCategory] ↔ [String]
 * - [LocalDate] ↔ [Long] (as epoch day)
 *
 * These conversions allow complex types to be persisted in the SQLite database.
 *
 * Room will automatically use these methods during database reads/writes
 * when annotated with [@TypeConverters].
 */
class Converters {

    /**
     * Converts [ExpenseCategory] enum to a [String] for storing in the database.
     * Example: ExpenseCategory.FOOD → "FOOD"
     *
     * @param value The enum value to convert
     * @return The string representation of the enum
     */
    @TypeConverter
    fun fromCategory(value: ExpenseCategory): String = value.name

    /**
     * Converts a [String] back into an [ExpenseCategory] enum.
     * Example: "TRANSPORT" → ExpenseCategory.TRANSPORT
     *
     * @param value The string from the database
     * @return The corresponding [ExpenseCategory] enum
     * @throws IllegalArgumentException if the value does not match any enum
     */
    @TypeConverter
    fun toCategory(value: String): ExpenseCategory = ExpenseCategory.valueOf(value)



    /**
     * Converts a [LocalDate] to a [Long] (epoch day) for persistence.
     * Example: 2025-07-26 → 19468
     *
     * @param date The [LocalDate] to convert
     * @return The epoch day representation
     */
    @TypeConverter
    fun fromDate(date: LocalDate): Long = date.toEpochDay()

    /**
     * Converts an epoch day [Long] value back into a [LocalDate].
     * Example: 19468 → LocalDate.of(2025, 7, 26)
     *
     * @param epochDay The epoch day to convert
     * @return The corresponding [LocalDate]
     */
    @TypeConverter
    fun toDate(epochDay: Long): LocalDate = LocalDate.ofEpochDay(epochDay)
}
