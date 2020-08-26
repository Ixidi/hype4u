package xyz.ixidi.hype4u.spigot.injection.module

import org.bukkit.plugin.Plugin
import org.koin.dsl.module
import xyz.ixidi.hype4u.spigot.framework.repository.PluginRepository
import xyz.ixidi.hype4u.spigot.config.PluginConfig
import xyz.ixidi.hype4u.spigot.framework.command.argument.ArgumentParsers
import xyz.ixidi.hype4u.spigot.framework.command.argument.ArgumentParsersImpl
import xyz.ixidi.hype4u.spigot.framework.command.parser.CommandParser
import xyz.ixidi.hype4u.spigot.framework.command.parser.CommandParserImpl
import xyz.ixidi.hype4u.spigot.framework.command.registration.CommandRegistration
import xyz.ixidi.hype4u.spigot.framework.command.registration.CommandRegistrationImpl
import xyz.ixidi.hype4u.spigot.framework.language.TranslationManager
import xyz.ixidi.hype4u.spigot.framework.language.TranslationManagerImpl
import xyz.ixidi.hype4u.spigot.framework.message.Messages
import xyz.ixidi.hype4u.spigot.framework.message.MessagesImpl
import xyz.ixidi.hype4u.spigot.framework.repository.PluginRepositoryImpl
import xyz.ixidi.hype4u.spigot.framework.task.TaskManager
import xyz.ixidi.hype4u.spigot.framework.task.TaskManagerImpl

fun frameworkModule(plugin: Plugin) = module {

    single { plugin }
    single { get<Plugin>().server }

    single<ArgumentParsers> { ArgumentParsersImpl(get()) }
    single<CommandParser> { CommandParserImpl(get()) }
    single<CommandRegistration> { CommandRegistrationImpl(get()) }

    single<PluginRepository> { PluginRepositoryImpl(get()) }

    single<TranslationManager> { TranslationManagerImpl(get()).apply { load(get<PluginConfig>().language) } }
    single<Messages> { MessagesImpl(get()) }

    single<TaskManager> { TaskManagerImpl(get()) }

}