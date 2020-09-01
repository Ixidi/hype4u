package xyz.ixidi.hype4u.core.module

import org.koin.dsl.module
import xyz.ixidi.hype4u.core.command.BroadcastCommand
import xyz.ixidi.hype4u.core.command.ChatCommand
import xyz.ixidi.hype4u.core.command.PunishmentCommands
import xyz.ixidi.hype4u.framework.Commands

val commandModule = module {

    single {
        Commands(get(), get()).apply {
            add(PunishmentCommands(get(), get(), get(), get(), get(), get()))
            add(BroadcastCommand(get(), get()))
            add(ChatCommand(get(), get()))
        }
    }

}