package xyz.ixidi.hype4u.core.module

import org.koin.dsl.module
import xyz.ixidi.hype4u.core.listener.PlayerChatListener
import xyz.ixidi.hype4u.core.listener.PlayerJoinListener
import xyz.ixidi.hype4u.core.listener.PlayerLoginListener
import xyz.ixidi.hype4u.core.listener.PlayerQuitListener
import xyz.ixidi.hype4u.framework.Listeners

internal val listenerModule = module {

    single { Listeners(get()).apply {
        add(PlayerChatListener(get()))
        add(PlayerJoinListener(get(), get(), get()))
        add(PlayerLoginListener(get(), get()))
        add(PlayerQuitListener(get(), get(), get()))
    } }

}