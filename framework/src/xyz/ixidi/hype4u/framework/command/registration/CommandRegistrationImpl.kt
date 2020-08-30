package xyz.ixidi.hype4u.framework.command.registration

import org.bukkit.Server
import org.bukkit.command.CommandMap
import xyz.ixidi.hype4u.framework.command.Command
import kotlin.reflect.full.declaredMemberProperties
import kotlin.reflect.jvm.isAccessible

internal class CommandRegistrationImpl(
    private val server: Server
) : CommandRegistration {

    private val commandMap by lazy {
        val field = server::class.declaredMemberProperties.first { it.name == "commandMap" }
        if (!field.isAccessible) field.isAccessible = true

        field.call(server) as CommandMap
    }

    override fun register(command: Command) {
        val performer = CommandPerformer(command)
        commandMap.register(command.name, "hypeplugin", performer)
    }

}