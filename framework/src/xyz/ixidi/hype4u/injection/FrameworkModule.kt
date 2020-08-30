package xyz.ixidi.hype4u.injection

import com.mysql.cj.jdbc.MysqlDataSource
import org.bukkit.plugin.Plugin
import org.jetbrains.exposed.sql.Database
import org.koin.dsl.module
import xyz.ixidi.hype4u.framework.repository.PluginRepository
import xyz.ixidi.hype4u.config.PluginConfig
import xyz.ixidi.hype4u.framework.Listeners
import xyz.ixidi.hype4u.framework.command.argument.ArgumentParsers
import xyz.ixidi.hype4u.framework.command.argument.ArgumentParsersImpl
import xyz.ixidi.hype4u.framework.command.parser.CommandParser
import xyz.ixidi.hype4u.framework.command.parser.CommandParserImpl
import xyz.ixidi.hype4u.framework.command.registration.CommandRegistration
import xyz.ixidi.hype4u.framework.command.registration.CommandRegistrationImpl
import xyz.ixidi.hype4u.framework.gui.GuiListener
import xyz.ixidi.hype4u.framework.gui.GuiManager
import xyz.ixidi.hype4u.framework.gui.GuiManagerImpl
import xyz.ixidi.hype4u.framework.language.TranslationManager
import xyz.ixidi.hype4u.framework.language.TranslationManagerImpl
import xyz.ixidi.hype4u.framework.message.Messages
import xyz.ixidi.hype4u.framework.message.MessagesImpl
import xyz.ixidi.hype4u.framework.repository.PluginRepositoryImpl
import xyz.ixidi.hype4u.framework.task.TaskManager
import xyz.ixidi.hype4u.framework.task.TaskManagerImpl
import xyz.ixidi.hype4u.misc.FrameworkTranslatableKey

internal fun frameworkModule(plugin: Plugin) = module {

    single { plugin }
    single { get<Plugin>().server }

    single<ArgumentParsers> { ArgumentParsersImpl(get()) }
    single<CommandParser> { CommandParserImpl(get()) }
    single<CommandRegistration> { CommandRegistrationImpl(get()) }

    single<PluginRepository> { PluginRepositoryImpl(get()) }

    single<TranslationManager> { TranslationManagerImpl(get()).apply { load(FrameworkTranslatableKey.values().toList(), get()) } }
    single<Messages> { MessagesImpl(get()) }

    single<TaskManager> { TaskManagerImpl(get()) }
    single<GuiManager> { GuiManagerImpl(get()) }

    single { PluginConfig(get()).apply { load() } }

    single {
        val source = get<PluginConfig>().run {
            MysqlDataSource().apply {
                serverName = mysqlHost
                port = mysqlPort
                databaseName = mysqlDatabase
                user = mysqlUser
                password = mysqlPassword
                serverTimezone = "UTC"
                useSSL = false
            }
        }

        Database.connect(source)
    }

    single { Listeners(get()).apply { add(GuiListener(get())) } }

}