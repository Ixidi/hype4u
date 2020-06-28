package xyz.ixidi.hype4u.core.plugin.listener

import org.bukkit.event.EventHandler
import org.bukkit.event.EventPriority
import org.bukkit.event.Listener
import org.bukkit.event.player.PlayerCommandSendEvent
import xyz.ixidi.hype4u.core.framework.command.registration.CommandRegistration
import xyz.ixidi.hype4u.core.framework.permission.group.GroupManager

internal class PlayerTabCompleteListener(
    private val commandRegistration: CommandRegistration,
    private val groupManager: GroupManager
) : Listener {

    @EventHandler(priority = EventPriority.HIGHEST, ignoreCancelled = false)
    fun onPlayerTabComplete(event: PlayerCommandSendEvent) {
        if (groupManager.getAllGroupsByPlayer(event.player).none { it.isRoot }) {
            event.commands.clear()
            event.commands.addAll(commandRegistration.getAvailableCommands(event.player))
        }
    }

}