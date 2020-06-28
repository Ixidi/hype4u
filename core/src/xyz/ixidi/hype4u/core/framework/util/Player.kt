package xyz.ixidi.hype4u.core.framework.util

import org.bukkit.entity.Player
import org.koin.core.get
import xyz.ixidi.hype4u.core.framework.gui.Gui
import xyz.ixidi.hype4u.core.framework.gui.GuiManager
import xyz.ixidi.hype4u.core.internal.injection.DependencyInjection

fun Player.openGui(gui: Gui) {
    DependencyInjection.get<GuiManager>().open(this, gui)
}