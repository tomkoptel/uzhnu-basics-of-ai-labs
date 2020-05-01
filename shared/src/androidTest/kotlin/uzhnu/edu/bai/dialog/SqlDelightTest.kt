package uzhnu.edu.bai.dialog

import android.app.Application
import uzhnu.edu.bai.dialog.db.BAIDB
import com.squareup.sqldelight.android.AndroidSqliteDriver
import com.squareup.sqldelight.db.SqlDriver
import androidx.test.core.app.ApplicationProvider

internal actual fun testDbConnection(): SqlDriver {
    val app = ApplicationProvider.getApplicationContext<Application>()
    return AndroidSqliteDriver(BAIDB.Schema, app, "baidb")
}
