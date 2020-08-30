package xyz.ixidi.hype4u.framework.plugin

import org.bukkit.plugin.Plugin
import org.jetbrains.exposed.sql.Database
import org.jetbrains.exposed.sql.SchemaUtils
import org.jetbrains.exposed.sql.transactions.transaction
import org.koin.core.get
import org.koin.dsl.module
import xyz.ixidi.hype4u.framework.command.argument.ArgumentParsers
import xyz.ixidi.hype4u.framework.command.parser.CommandParser
import xyz.ixidi.hype4u.framework.command.registration.CommandRegistration
import xyz.ixidi.hype4u.framework.gui.GuiManager
import xyz.ixidi.hype4u.framework.language.TranslationManager
import xyz.ixidi.hype4u.framework.message.Messages
import xyz.ixidi.hype4u.framework.repository.PluginRepository
import xyz.ixidi.hype4u.framework.repository.PluginRepositoryImpl
import xyz.ixidi.hype4u.framework.task.TaskManager
import xyz.ixidi.hype4u.framework.injection.FrameworkDependencyInjection

internal fun externalPluginModule(plugin: Plugin, pluginInfo: PluginInfo) = module {

    single { plugin }
    single { get<Plugin>().server }

    single<ArgumentParsers> { FrameworkDependencyInjection.get() }
    single<CommandParser> { FrameworkDependencyInjection.get() }
    single<CommandRegistration> { FrameworkDependencyInjection.get() }

    single<PluginRepository> { PluginRepositoryImpl(get()) }

    single { FrameworkDependencyInjection.get<TranslationManager>() }
    single<Messages> { FrameworkDependencyInjection.get() }

    single<TaskManager> { FrameworkDependencyInjection.get() }
    single<GuiManager> { FrameworkDependencyInjection.get() }

    single {
        FrameworkDependencyInjection.get<Database>().apply {
            transaction(this) {
                SchemaUtils.createMissingTablesAndColumns(*pluginInfo.databaseTables.toTypedArray())
            }
        }
    }

}