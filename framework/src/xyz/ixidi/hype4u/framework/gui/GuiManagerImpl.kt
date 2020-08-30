package xyz.ixidi.hype4u.framework.gui

import org.bukkit.Server
import org.bukkit.entity.Player
import xyz.ixidi.hype4u.framework.gui.Gui
import java.util.*
import kotlin.collections.HashMap

internal class GuiManagerImpl(
    private val server: Server
) : GuiManager {

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