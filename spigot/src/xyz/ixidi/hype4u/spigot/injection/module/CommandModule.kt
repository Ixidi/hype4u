package xyz.ixidi.hype4u.spigot.injection.module

import org.koin.dsl.module
import xyz.ixidi.hype4u.spigot.core.command.PunishmentCommands
import xyz.ixidi.hype4u.spigot.misc.Commands

val commandModule = module {

    single { PunishmentCommands(get(), get(), get()) }

    single {
        Commands(get(), get()).apply {
            add(get<PunishmentCommands>())
        }
    }

}