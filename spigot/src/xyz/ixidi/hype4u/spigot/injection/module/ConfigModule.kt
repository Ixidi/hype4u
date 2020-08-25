package xyz.ixidi.hype4u.spigot.injection.module

import org.koin.dsl.module
import xyz.ixidi.hype4u.spigot.config.PluginConfig

val configModule = module {

    single { PluginConfig(get()).apply { load() } }

}