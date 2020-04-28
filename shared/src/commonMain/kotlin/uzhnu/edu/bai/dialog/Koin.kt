package uzhnu.edu.bai.dialog

import uzhnu.edu.bai.dialog.ktor.DogApiImpl
import uzhnu.edu.bai.dialog.ktor.KtorApi
import org.koin.core.context.startKoin
import org.koin.core.module.Module
import org.koin.dsl.KoinAppDeclaration
import org.koin.dsl.module

fun initKoin(appDeclaration: KoinAppDeclaration = {}) = startKoin {
    appDeclaration()
    modules(platformModule, coreModule)
}

private val coreModule = module {
    single { DatabaseHelper(get()) }
    single<KtorApi> { DogApiImpl() }
}

expect val platformModule: Module
