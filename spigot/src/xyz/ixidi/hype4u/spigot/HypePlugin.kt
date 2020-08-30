package xyz.ixidi.hype4u.spigot

import org.bukkit.event.player.PlayerJoinEvent
import org.bukkit.event.player.PlayerQuitEvent
import org.bukkit.plugin.java.JavaPlugin
import org.koin.core.get
import xyz.ixidi.hype4u.spigot.injection.DependencyInjection
import xyz.ixidi.hype4u.spigot.misc.Commands
import xyz.ixidi.hype4u.spigot.misc.Listeners

class HypePlugin : JavaPlugin() {

    override fun onEnable() {
        DependencyInjection.run {
            start(this@HypePlugin)
            get<Commands>().registerAll()
            get<Listeners>().registerAll()
        }
        server.onlinePlayers.forEach {
            server.pluginManager.callEvent(PlayerJoinEvent(it, ""))
        }
    }

    override fun onDisable() {
        server.onlinePlayers.forEach {
            server.pluginManager.callEvent(PlayerQuitEvent(it, ""))
        }
    }

}