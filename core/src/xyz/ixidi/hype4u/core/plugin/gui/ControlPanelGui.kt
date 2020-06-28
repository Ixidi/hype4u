package xyz.ixidi.hype4u.core.plugin.gui

import org.bukkit.Material
import org.bukkit.entity.Player
import org.bukkit.event.inventory.InventoryCloseEvent
import xyz.ixidi.hype4u.core.framework.gui.Behaviour
import xyz.ixidi.hype4u.core.framework.gui.GuiAction
import xyz.ixidi.hype4u.core.framework.gui.GuiItem
import xyz.ixidi.hype4u.core.framework.gui.InventoryGui
import xyz.ixidi.hype4u.core.framework.permission.group.GroupManager
import xyz.ixidi.hype4u.core.framework.util.itemStack
import xyz.ixidi.hype4u.core.framework.util.languageString
import xyz.ixidi.hype4u.core.permission.CoreLangKeys
import xyz.ixidi.hype4u.core.permission.CorePermissions

class ControlPanelGui(
    private val player: Player,
    private val groupManager: GroupManager
) : InventoryGui(6, "&4&lPanel") {

    override fun initialize() {
        if (player.hasPermission(CorePermissions.MANAGER_GROUPS)) {
            setSlot(
                2,
                GuiItem(
                    itemStack(Material.DIAMOND, name = languageString(CoreLangKeys.GROUPS_MANAGEMENT_GUI_ITEM_TITLE)),
                    GuiAction.openGui(GroupChooseGui(groupManager))
                ))
        }

        fillEmpty()
    }

    override fun refresh() {}

    override fun handleClose(event: InventoryCloseEvent): Behaviour {
        TODO("Not yet implemented")
    }

}