package com.albayemre.expensetrackingapplication

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

/**
 * ExpenseTrackingApplication is the base Application class for the app.
 *
 * It is annotated with @HiltAndroidApp, which triggers Hilt's code generation
 * and prepares the application for dependency injection.
 *
 * This is required for Hilt to inject dependencies into Android components
 * such as Activities, Fragments, ViewModels, Services, and more.
 */
@HiltAndroidApp
class ExpenseTrackingApplication : Application()
