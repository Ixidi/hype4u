package xyz.ixidi.hype4u.spigot.injection.module

import org.koin.dsl.module
import xyz.ixidi.hype4u.spigot.core.listener.PlayerChatListener
import xyz.ixidi.hype4u.spigot.core.listener.PlayerJoinListener
import xyz.ixidi.hype4u.spigot.core.listener.PlayerQuitListener
import xyz.ixidi.hype4u.spigot.framework.gui.GuiListener
import xyz.ixidi.hype4u.spigot.misc.Listeners

val listenerModule = module {

    single { PlayerQuitListener(get(), get(), get()) }
    single { PlayerJoinListener(get(), get(), get()) }
    single { PlayerChatListener(get()) }

    single {
        Listeners(get()).apply {
            add(get<PlayerJoinListener>())
            add(get<PlayerQuitListener>())
            add(get<GuiListener>())
            add(get<PlayerChatListener>())
        }
    }

}