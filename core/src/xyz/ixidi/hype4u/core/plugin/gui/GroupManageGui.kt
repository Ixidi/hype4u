package xyz.ixidi.hype4u.core.plugin.gui

import org.bukkit.event.inventory.InventoryCloseEvent
import xyz.ixidi.hype4u.core.framework.gui.Behaviour
import xyz.ixidi.hype4u.core.framework.gui.Behaviours
import xyz.ixidi.hype4u.core.framework.gui.InventoryGui
import xyz.ixidi.hype4u.core.framework.permission.group.Group

class GroupManageGui(
    private val group: Group
) : InventoryGui(1, group.name) {

    override fun initialize() {
        TODO("Not yet implemented")
    }

    override fun refresh() {
        TODO("Not yet implemented")
    }

    override fun handleClose(event: InventoryCloseEvent): Behaviour = Behaviours.CLOSE

}