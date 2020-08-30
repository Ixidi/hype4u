package xyz.ixidi.hype4u.core.module

import org.koin.dsl.module
import xyz.ixidi.hype4u.core.command.PunishmentCommands
import xyz.ixidi.hype4u.framework.Commands

val commandModule = module {

    single {
        Commands(get(), get()).apply {
            add(PunishmentCommands(get(), get(), get()))
        }
    }

}