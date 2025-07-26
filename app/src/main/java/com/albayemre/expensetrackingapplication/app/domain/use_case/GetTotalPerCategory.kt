package com.albayemre.expensetrackingapplication.app.domain.use_case

import com.albayemre.expensetrackingapplication.app.data.repository.ExpenseRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

/**
 * Use case for retrieving the total spending grouped by expense category.
 *
 * Example output:
 * ```
 * [("FOOD", 120.0), ("TRANSPORT", 80.0), ("BILLS", 50.0)]
 * ```
 *
 * This is useful for visualizations and summary views in the UI layer.
 */
class GetTotalPerCategory @Inject constructor(
    private val repository: ExpenseRepository
) {

    /**
     * Returns a [Flow] that emits a list of pairs where:
     * - First: category name as [String]
     * - Second: total amount spent in that category as [Double]
     *
     * The flow automatically emits updated values when the underlying data changes.
     *
     * @return [Flow]<List<Pair<String, Double>>> representing category-wise totals
     */
    operator fun invoke(): Flow<List<Pair<String, Double>>> {
        return repository.getTotalPerCategory()
    }
}
