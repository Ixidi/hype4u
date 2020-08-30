package xyz.ixidi.hype4u.framework.injection

import org.jetbrains.exposed.sql.Database
import org.koin.core.Koin
import org.koin.core.KoinComponent
import org.koin.core.get
import org.koin.core.module.Module
import org.koin.dsl.koinApplication

internal open class DependencyInjection : KoinComponent {

    private var koin: Koin? = null

    fun start(modules: List<Module>) {
        if (koin != null) {
            throw IllegalStateException("Koin is already enabled.")
        }

        koin = koinApplication {
            modules(modules)
        }.koin
    }

    fun stop() {
        getKoin().close()
    }

    override fun getKoin(): Koin {
        return koin ?: throw IllegalStateException("Koin is not enabled.")
    }

}