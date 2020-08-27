package xyz.ixidi.hype4u.spigot.misc

import org.bukkit.event.Listener
import org.bukkit.plugin.Plugin

class Listeners(
    private val plugin: Plugin
) {

    private val listeners = ArrayList<Listener>()

    fun add(listener: Listener) {
        listeners.add(listener)
    }

    fun registerAll() {
        listeners.forEach {
            plugin.server.pluginManager.registerEvents(it, plugin)
        }
    }

}