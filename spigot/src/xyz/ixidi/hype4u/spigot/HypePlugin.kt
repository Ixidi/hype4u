package xyz.ixidi.hype4u.spigot

import org.bukkit.plugin.java.JavaPlugin
import org.koin.core.get
import xyz.ixidi.hype4u.spigot.injection.DependencyInjection
import xyz.ixidi.hype4u.spigot.misc.Commands

class HypePlugin : JavaPlugin() {

    override fun onEnable() {
        DependencyInjection.start(this)
        DependencyInjection.get<Commands>().registerAll()
    }

}