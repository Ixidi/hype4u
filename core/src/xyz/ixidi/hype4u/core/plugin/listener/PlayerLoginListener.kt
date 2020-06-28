package xyz.ixidi.hype4u.core.plugin.listener

import org.bukkit.Bukkit
import org.bukkit.event.EventHandler
import org.bukkit.event.EventPriority
import org.bukkit.event.Listener
import org.bukkit.event.player.PlayerLoginEvent
import xyz.ixidi.hype4u.core.framework.permission.group.GroupManager
import xyz.ixidi.hype4u.core.internal.permission.HypePermissibleBase
import java.lang.reflect.Modifier

internal class PlayerLoginListener(
    private val groupManager: GroupManager
) : Listener {

    @EventHandler(priority = EventPriority.LOWEST, ignoreCancelled = false)
    fun onPlayerLogin(event: PlayerLoginEvent) {
        if (groupManager.getPrimaryGroupByPlayer(event.player) == null) {
            val group = groupManager.getDefaultGroup()
            group.addMember(event.player)
            groupManager.setAsPrimaryGroup(event.player, group)
        }

        val version = Bukkit.getServer().javaClass.`package`.name.split(".")[3]
        Class.forName("org.bukkit.craftbukkit.$version.entity.CraftHumanEntity").getDeclaredField("perm").let {
            it.isAccessible = true

            it.javaClass.getDeclaredField("modifiers").let { modifiersField ->
                modifiersField.isAccessible = true

                modifiersField.setInt(it, it.modifiers and Modifier.FINAL.inv())
            }

            it.set(event.player, HypePermissibleBase(event.player, groupManager))
        }
    }

}