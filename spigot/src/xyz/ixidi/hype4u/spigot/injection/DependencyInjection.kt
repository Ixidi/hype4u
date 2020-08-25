package xyz.ixidi.hype4u.spigot.injection

import org.bukkit.plugin.Plugin
import org.bukkit.plugin.java.JavaPlugin
import org.koin.core.Koin
import org.koin.core.KoinComponent
import org.koin.dsl.koinApplication
import xyz.ixidi.hype4u.spigot.injection.module.*

internal object DependencyInjection : KoinComponent {

    private var koin: Koin? = null

    private fun koinModules(plugin: Plugin) = arrayListOf(
        frameworkModule(plugin),
        configModule,
        commandModule
    )

    fun start(plugin: JavaPlugin) {
        if (koin != null) {
            throw IllegalStateException("Koin is already enabled.")
        }

        koin = koinApplication {
            modules(koinModules(plugin))
        }.koin
    }

    fun stop() {
        getKoin().close()
    }

    override fun getKoin(): Koin {
        return koin ?: throw IllegalStateException("Koin is not enabled.")
    }

}