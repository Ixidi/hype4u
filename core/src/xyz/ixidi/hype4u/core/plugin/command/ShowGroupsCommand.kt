package xyz.ixidi.hype4u.core.plugin.command

import xyz.ixidi.hype4u.core.framework.command.baseCommand
import xyz.ixidi.hype4u.core.framework.permission.group.GroupManager
import xyz.ixidi.hype4u.core.framework.util.message
import xyz.ixidi.hype4u.core.permission.CoreLangKeys

internal fun showGroupsCommand(groupManager: GroupManager) = baseCommand { sender, _ ->
    val groups = groupManager.getAllGroups().joinToString("&7, ") {
        var name = if (it.isRoot) {
            "&4${it.name}"
        } else {
            "&7${it.name}"
        }

        if (it == groupManager.getDefaultGroup()) {
            name = "&9&lD $name"
        }

        name
    }

    sender.message(CoreLangKeys.GROUPS_DISPLAY, "groups" to groups)
}