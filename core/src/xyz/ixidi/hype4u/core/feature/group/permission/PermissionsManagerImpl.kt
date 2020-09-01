package xyz.ixidi.hype4u.core.feature.group.permission

import org.bukkit.entity.Player
import org.bukkit.permissions.PermissionAttachment
import org.bukkit.plugin.Plugin
import xyz.ixidi.hype4u.core.user.UserManager
import java.util.*
import kotlin.collections.HashMap

class PermissionsManagerImpl(
    private val plugin: Plugin,
    private val userManager: UserManager
) : PermissionsManager {

    private val attachmentMap = HashMap<UUID, PermissionAttachment>()

    override fun givePermissions(player: Player) {
        val attachment = player.addAttachment(plugin)
        val user = userManager.getOnlineUser(player)
        val groups = user.userGroups

        groups.secondaryGroups().plus(groups.primaryGroup()).forEach { give(attachment, it.getAllPermissions()) }
        attachmentMap[player.uniqueId] = attachment
    }

    override fun removePermissions(player: Player) {
        val attachment = attachmentMap.remove(player.uniqueId) ?: return
        player.removeAttachment(attachment)
    }

    private fun give(attachment: PermissionAttachment, permissions: Map<String, Boolean>) {
        permissions.forEach { (perm, allow) ->
            attachment.setPermission(perm, allow)
        }
    }

}