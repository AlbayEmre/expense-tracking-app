package com.albayemre.expensetrackingapplication.app.data.local

/**
 * Represents the set of predefined categories that an expense can belong to.
 *
 * Used in:
 * - [ExpenseEntity] as a field
 * - Filters, visualizations, and category-based aggregations
 * - Dropdowns for user input
 *
 * Note:
 * This enum is persisted in the Room database using a [TypeConverter],
 * which maps it to and from a [String] (e.g., FOOD ↔ "FOOD").
 */
enum class ExpenseCategory {

    /**
     * Food and beverage-related expenses.
     * Examples: groceries, restaurants, coffee, snacks.
     */
    FOOD,

    /**
     * All transportation costs.
     * Examples: public transit, fuel, taxi, Uber, tolls.
     */
    TRANSPORT,

    /**
     * Monthly or recurring bills.
     * Examples: electricity, water, gas, internet, phone.
     */
    BILLS,

    /**
     * Entertainment and leisure spending.
     * Examples: movies, games, concerts, subscriptions (e.g., Netflix).
     */
    ENTERTAINMENT,

    /**
     * Any expenses that do not fall into the defined categories.
     * Examples: gifts, one-time purchases, unexpected fees.
     */
    OTHER
}
