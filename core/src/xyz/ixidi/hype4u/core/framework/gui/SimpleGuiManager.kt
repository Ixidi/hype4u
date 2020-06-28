package xyz.ixidi.hype4u.core.framework.gui

import org.bukkit.entity.Player
import java.util.*
import kotlin.collections.HashMap

internal class SimpleGuiManager : GuiManager {

    private val openedGui = HashMap<UUID, Gui>()

    override fun open(player: Player, gui: Gui) {
        gui.open(player)
        openedGui[player.uniqueId] = gui
    }

    override fun get(player: Player) = openedGui[player.uniqueId]

    override fun remove(player: Player) {
        if (openedGui.containsKey(player.uniqueId)) {
            openedGui.remove(player.uniqueId)
        }
    }

}