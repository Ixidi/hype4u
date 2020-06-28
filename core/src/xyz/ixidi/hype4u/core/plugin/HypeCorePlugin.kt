package xyz.ixidi.hype4u.core.plugin

import org.bukkit.Material
import org.koin.core.get
import xyz.ixidi.hype4u.core.framework.HypePlugin
import xyz.ixidi.hype4u.core.framework.gui.GuiListener
import xyz.ixidi.hype4u.core.framework.permission.group.Group
import xyz.ixidi.hype4u.core.internal.injection.DependencyInjection
import xyz.ixidi.hype4u.core.internal.permission.group.GroupArgumentHandler
import xyz.ixidi.hype4u.core.permission.CorePermissions
import xyz.ixidi.hype4u.core.plugin.command.controlPanelCommand
import xyz.ixidi.hype4u.core.plugin.command.createGroupCommand
import xyz.ixidi.hype4u.core.plugin.command.showGroupsCommand
import xyz.ixidi.hype4u.core.plugin.listener.PlayerLoginListener
import xyz.ixidi.hype4u.core.plugin.listener.PlayerTabCompleteListener

internal class HypeCorePlugin : HypePlugin("core", "Główne funkcje.", Material.EMERALD) {

    override fun onLoad() {
        DependencyInjection.start(this, dataFolder)
    }

    override fun onEnable() {
        registerMessages("messages.yml")

        registerListeners(
            GuiListener(DependencyInjection.get()),
            PlayerLoginListener(DependencyInjection.get()),
            PlayerTabCompleteListener(DependencyInjection.get(), DependencyInjection.get())
        )

        registerCommandArgumentHandler(Group::class, GroupArgumentHandler::class, GroupArgumentHandler(groupManager))

        registerCommand(
            name = "controlpanel",
            command = controlPanelCommand(groupManager),
            description = "Panel kontrolny.",
            aliases = listOf("cp"),
            permission = CorePermissions.MANAGER_GROUPS
        )

        registerCommand(
            name = "creategroup",
            command = createGroupCommand(groupManager),
            description = "Tworzy nowa groupe na serwerze.",
            permission = CorePermissions.MANAGER_GROUPS
        )

        registerCommand(
            name = "showgroups",
            command = showGroupsCommand(groupManager),
            description = "Lista wszystkich grup.",
            permission = CorePermissions.MANAGER_GROUPS
        )
    }

}