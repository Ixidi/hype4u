package xyz.ixidi.hype4u.core.framework

import org.bukkit.Material
import org.bukkit.event.Listener
import org.bukkit.plugin.ServicesManager
import org.bukkit.plugin.java.JavaPlugin
import org.koin.core.get
import xyz.ixidi.hype4u.core.framework.command.Command
import xyz.ixidi.hype4u.core.framework.command.registration.CommandRegistration
import xyz.ixidi.hype4u.core.framework.command.rich.argument.handler.ArgumentHandler
import xyz.ixidi.hype4u.core.framework.command.rich.argument.handler.ArgumentHandlerManager
import xyz.ixidi.hype4u.core.framework.model.data.lang.Language
import xyz.ixidi.hype4u.core.framework.permission.group.GroupManager
import xyz.ixidi.hype4u.core.framework.util.YamlFile
import xyz.ixidi.hype4u.core.internal.SimplePluginIdentifier
import xyz.ixidi.hype4u.core.internal.injection.DependencyInjection
import java.io.File
import kotlin.reflect.KClass

abstract class HypePlugin(
    shortName: String,
    description: String,
    guiIconMaterial: Material
) : JavaPlugin() {

    protected val identifier: PluginIdentifier = SimplePluginIdentifier(shortName, description, guiIconMaterial)
    protected val serviceManager by lazy { DependencyInjection.get<ServicesManager>() }
    protected val groupManager by lazy { DependencyInjection.get<GroupManager>() }

    private val commandRegistration by lazy { DependencyInjection.get<CommandRegistration>() }
    private val language by lazy {  DependencyInjection.get<Language>() }
    private val argumentHandlerManager by lazy {  DependencyInjection.get<ArgumentHandlerManager>() }

    protected fun registerCommand(
        name: String,
        command: Command,
        description: String = "Komenda pluginu.",
        aliases: List<String> = emptyList(),
        permission: String? = null
    ) {
        commandRegistration.registerCommand(this, name, description, aliases, permission, command)
    }

    protected fun <T : Any, H : ArgumentHandler<T>> registerCommandArgumentHandler(
        valueClass: KClass<T>,
        handlerClass: KClass<H>,
        handler: ArgumentHandler<T>
    ) {
        argumentHandlerManager.registerHandler(valueClass, handlerClass, handler)
    }

    protected inline fun <reified T : Any, reified H : ArgumentHandler<T>> registerCommandArgumentHandler(handler: ArgumentHandler<T>) {
        registerCommandArgumentHandler(T::class, H::class, handler)
    }

    protected fun registerMessages(fileName: String) {
        language.registerStringYaml(identifier.name, yamlResource(fileName))
    }


    protected fun registerListeners(vararg listeners: Listener) {
        listeners.forEach { server.pluginManager.registerEvents(it, this) }
    }

    protected fun yamlResource(name: String): YamlFile {
        val file = File(dataFolder, name)
        if (!file.exists()) saveResource(name, false)
        return YamlFile(file)
    }

}