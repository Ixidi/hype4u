package xyz.ixidi.hype4u.core.plugin.command

import xyz.ixidi.hype4u.core.framework.command.playerCommand
import xyz.ixidi.hype4u.core.framework.permission.group.GroupManager
import xyz.ixidi.hype4u.core.framework.util.openGui
import xyz.ixidi.hype4u.core.plugin.gui.ControlPanelGui

internal fun controlPanelCommand(groupManager: GroupManager) = playerCommand { player, _ ->
    player.openGui(
        ControlPanelGui(player, groupManager)
    )
}