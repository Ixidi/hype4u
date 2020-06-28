package xyz.ixidi.hype4u.core.internal.injection

import org.bukkit.plugin.Plugin
import org.koin.core.Koin
import org.koin.core.KoinComponent
import org.koin.dsl.koinApplication
import xyz.ixidi.hype4u.core.internal.injection.module.apiModule
import xyz.ixidi.hype4u.core.internal.injection.module.dataModule
import java.io.File

internal object DependencyInjection : KoinComponent {

    private var koin: Koin? = null

    private fun modules(plugin: Plugin, dataFolder: File) = listOf(
        apiModule(plugin),
        dataModule(dataFolder)
    )

    fun start(plugin: Plugin, dataFolder: File) {
        if (!dataFolder.exists()) dataFolder.mkdirs()

        koin = koinApplication {
            modules(modules(plugin, dataFolder))
        }.koin
    }

    override fun getKoin(): Koin {
        return koin!!
    }

}