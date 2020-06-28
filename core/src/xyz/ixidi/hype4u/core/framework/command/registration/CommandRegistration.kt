package xyz.ixidi.hype4u.core.framework.command.registration

import org.bukkit.Bukkit
import org.bukkit.command.CommandMap
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player
import org.bukkit.plugin.Plugin
import xyz.ixidi.hype4u.core.framework.command.Command
import xyz.ixidi.hype4u.core.framework.util.message
import xyz.ixidi.hype4u.core.permission.CoreLangKeys

internal class CommandRegistration  {

    // commandName - permission
    private val commandsPermissionMap = HashMap<String, String?>()

    private class BukkitCommandWrapper(
        name: String,
        description: String,
        aliases: List<String>,
        val command: Command,
        val perm: String? = null
    ) : org.bukkit.command.Command(name, description, "", aliases) {

        override fun execute(sender: CommandSender, commandLabel: String, args: Array<out String>): Boolean {
            if (perm != null && !sender.hasPermission(perm)) {
                sender.message(CoreLangKeys.PERMISSION_MESSAGE)
                return true
            }

            command.onExecute(sender, this.name, args.toList())
            return true
        }

        override fun tabComplete(sender: CommandSender, alias: String, args: Array<out String>): MutableList<String>
                = command.onTabComplete(sender, args.toList()).toMutableList()

    }

    private val commandMap by lazy {
        val field = Bukkit.getServer()::class.java.getDeclaredField("commandMap")
        if (!field.isAccessible) field.isAccessible = true
        field.get(Bukkit.getServer()) as CommandMap
    }

    fun registerCommand(
        plugin: Plugin,
        name: String,
        description: String,
        aliases: List<String>,
        permission: String?,
        command: Command
    ) {
        val wrapper = BukkitCommandWrapper(name, description, aliases, command)
        commandMap.register(plugin.name.toLowerCase(), wrapper)
        commandsPermissionMap[name] = permission
    }

    fun getAvailableCommands(player: Player) =
        commandsPermissionMap.filter { it.value?.let { perm -> player.hasPermission(perm) } ?: true }.map { it.key }

}