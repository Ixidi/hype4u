package xyz.ixidi.hype4u.core.module

import org.koin.dsl.module
import xyz.ixidi.hype4u.core.listener.*
import xyz.ixidi.hype4u.framework.Listeners

internal val listenerModule = module {

    single { Listeners(get()).apply {
        add(PlayerChatListener(get(), get()))
        add(PlayerJoinListener(get(), get()))
        add(PlayerQuitListener(get(), get(), get()))
        add(AsyncPlayerPreLoginListener(get(), get(), get(), get()))
    } }

}