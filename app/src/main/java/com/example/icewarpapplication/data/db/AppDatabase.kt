package com.example.icewarpapplication.data.db

import android.content.Context
import app.cash.sqldelight.driver.android.AndroidSqliteDriver

/**
 * A helper class to manage the SQLDelight database.
 * Renamed from AppDatabase to avoid conflict with the generated IcewarpDatabase class.
 */
class DatabaseHelper(context: Context) {

    private val driver = AndroidSqliteDriver(
        IcewarpDatabase.Schema,
        context,
        "app.db"
    )

    val database = IcewarpDatabase(driver)
}
