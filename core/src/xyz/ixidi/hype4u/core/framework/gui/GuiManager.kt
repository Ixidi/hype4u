package xyz.ixidi.hype4u.core.framework.gui

import org.bukkit.entity.Player

interface GuiManager {

    fun open(player: Player, gui: Gui)
    fun get(player: Player): Gui?
    fun remove(player: Player)

}