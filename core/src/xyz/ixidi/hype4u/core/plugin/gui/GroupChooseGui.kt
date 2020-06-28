package xyz.ixidi.hype4u.core.plugin.gui

import org.bukkit.Material
import org.bukkit.event.inventory.InventoryClickEvent
import org.bukkit.event.inventory.InventoryCloseEvent
import xyz.ixidi.hype4u.core.framework.gui.Behaviour
import xyz.ixidi.hype4u.core.framework.gui.Behaviours
import xyz.ixidi.hype4u.core.framework.gui.SelectionGui
import xyz.ixidi.hype4u.core.framework.permission.group.Group
import xyz.ixidi.hype4u.core.framework.permission.group.GroupManager
import xyz.ixidi.hype4u.core.framework.util.itemStack
import xyz.ixidi.hype4u.core.framework.util.languageString
import xyz.ixidi.hype4u.core.framework.util.message
import xyz.ixidi.hype4u.core.permission.CoreLangKeys

internal class GroupChooseGui(
    private val groupManager: GroupManager
) : SelectionGui<Group>(
    languageString(CoreLangKeys.SELECT_GROUP_GUI_TITLE),
    run {
        groupManager.getAllGroups().map {
            Option(it, itemStack(Material.PAPER, name = it.name))
        }
    }
) {

    override fun handleChoose(chosen: Group, clickEvent: InventoryClickEvent): Behaviour {
        if (!groupManager.getAllGroups().contains(chosen)) {
            clickEvent.whoClicked.message(CoreLangKeys.GROUP_HAS_BEEN_REMOVED_MESSAGE)
            return Behaviours.CLOSE
        }

        return Behaviours.openGui(GroupManageGui(chosen))
    }

    override fun handleClose(event: InventoryCloseEvent): Behaviour = Behaviours.CLOSE

}