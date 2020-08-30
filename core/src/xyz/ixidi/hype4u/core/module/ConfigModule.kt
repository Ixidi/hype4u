package xyz.ixidi.hype4u.core.module

import org.koin.dsl.module
import xyz.ixidi.hype4u.core.config.GroupConfig
import xyz.ixidi.hype4u.core.config.PluginConfig

val configModule = module {

    single { PluginConfig(get()).apply { load() } }
    single { GroupConfig(get()).apply { load() } }

}