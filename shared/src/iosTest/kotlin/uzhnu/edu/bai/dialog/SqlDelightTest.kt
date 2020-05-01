package uzhnu.edu.bai.dialog

import uzhnu.edu.bai.dialog.db.BAIDB
import com.squareup.sqldelight.db.SqlDriver
import com.squareup.sqldelight.drivers.native.NativeSqliteDriver

internal actual fun testDbConnection(): SqlDriver = NativeSqliteDriver(BAIDB.Schema, "baidb")
