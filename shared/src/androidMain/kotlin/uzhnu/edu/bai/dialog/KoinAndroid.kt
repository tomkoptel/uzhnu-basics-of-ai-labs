package uzhnu.edu.bai.dialog

import android.content.Context
import android.content.SharedPreferences
import com.russhwolf.settings.AndroidSettings
import com.russhwolf.settings.Settings
import com.squareup.sqldelight.android.AndroidSqliteDriver
import com.squareup.sqldelight.db.SqlDriver
import org.koin.core.module.Module
import org.koin.dsl.module
import uzhnu.edu.bai.dialog.db.BAIDB

actual val platformModule: Module = module {
    single<SqlDriver> {
        AndroidSqliteDriver(
            BAIDB.Schema,
            get(),
            "BAIDB"
        )
    }

    single<Settings> {
        val context: Context = get()
        val preferences: SharedPreferences =
            context.getSharedPreferences("BAIDB_SETTINGS", Context.MODE_PRIVATE)
        AndroidSettings(preferences)
    }
}
