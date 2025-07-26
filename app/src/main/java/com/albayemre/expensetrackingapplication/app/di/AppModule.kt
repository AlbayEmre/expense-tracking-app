package com.albayemre.expensetrackingapplication.app.di

import android.app.Application
import androidx.room.Room
import com.albayemre.expensetrackingapplication.app.data.local.ExpenseDatabase
import com.albayemre.expensetrackingapplication.app.data.repository.ExpenseRepository
import com.albayemre.expensetrackingapplication.app.data.repository.ExpenseRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

/**
 * Application-level Hilt module responsible for providing singleton dependencies.
 *
 * Annotated with @Module and @InstallIn(SingletonComponent::class), making it active
 * throughout the entire app lifecycle.
 */
@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    /**
     * Provides a singleton instance of the Room database.
     *
     * @param app Application context required to initialize Room
     * @return ExpenseDatabase instance connected to "expenses_db"
     */
    @Provides
    @Singleton
    fun provideDatabase(app: Application): ExpenseDatabase =
        Room.databaseBuilder(
            app,
            ExpenseDatabase::class.java,
            "expenses_db"
        ).build()

    /**
     * Provides an implementation of [ExpenseRepository] using the Room DAO.
     *
     * @param db The [ExpenseDatabase] instance provided above
     * @return ExpenseRepository used throughout the app
     */
    @Provides
    @Singleton
    fun provideExpenseRepository(db: ExpenseDatabase): ExpenseRepository =
        ExpenseRepositoryImpl(db.expenseDao)
}
