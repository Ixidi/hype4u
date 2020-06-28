package xyz.ixidi.hype4u.core.plugin.command

import xyz.ixidi.hype4u.core.framework.command.rich.argument.Argument
import xyz.ixidi.hype4u.core.framework.command.richCommand
import xyz.ixidi.hype4u.core.framework.permission.group.Group
import xyz.ixidi.hype4u.core.framework.util.message
import xyz.ixidi.hype4u.core.internal.permission.group.GroupArgumentHandler
import xyz.ixidi.hype4u.core.permission.CoreLangKeys

internal data class ShowGroupPermissionsCommandArgumentModel(
    @Argument(GroupArgumentHandler::class, "grupa")
    val group: Group
)

internal val showGroupPermissionsCommand = richCommand<ShowGroupPermissionsCommandArgumentModel> { sender ->
    val permissions = if (!group.isRoot) group.getAllPermissions().joinToString("&7, ") { "&a$it" } else "&4&lROOT (*)"

    sender.message(CoreLangKeys.GROUP_PERMISSIONS_DISPLAY, "group" to group.name, "permissions" to permissions)
}