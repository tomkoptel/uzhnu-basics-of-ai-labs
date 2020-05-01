package uzhnu.edu.bai.dialog.android

import android.app.Application
import android.content.Context
import uzhnu.edu.bai.dialog.initKoin
import org.koin.dsl.module

class MainApp : Application() {
    override fun onCreate() {
        super.onCreate()
        initKoin {
            modules(module { single<Context> { this@MainApp } })
        }
    }
}
