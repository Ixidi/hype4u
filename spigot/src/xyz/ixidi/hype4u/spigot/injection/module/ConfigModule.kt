package xyz.ixidi.hype4u.spigot.injection.module

import org.koin.dsl.module
import xyz.ixidi.hype4u.spigot.config.GroupConfig
import xyz.ixidi.hype4u.spigot.config.PluginConfig
import kotlin.math.sin

val configModule = module {

    single { PluginConfig(get()).apply { load() } }
    single { GroupConfig(get()).apply { load() } }

}