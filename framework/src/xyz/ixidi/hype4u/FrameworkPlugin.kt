package xyz.ixidi.hype4u

import org.bukkit.plugin.java.JavaPlugin
import org.koin.core.get
import xyz.ixidi.hype4u.framework.Listeners
import xyz.ixidi.hype4u.framework.message.Messages
import xyz.ixidi.hype4u.injection.FrameworkDependencyInjection
import xyz.ixidi.hype4u.injection.frameworkModule
import xyz.ixidi.hype4u.misc.FrameworkTranslatableKey

internal class FrameworkPlugin : JavaPlugin() {

    override fun onEnable() {
        FrameworkDependencyInjection.run {
            start(listOf(frameworkModule(this@FrameworkPlugin)))
            get<Listeners>().registerAll()
        }
    }

    override fun onDisable() {
        val message = FrameworkDependencyInjection.get<Messages>().getColoredMessage(FrameworkTranslatableKey.MESSAGE_RELOAD)
        server.onlinePlayers.forEach {
            it.kickPlayer(message)
        }
        FrameworkDependencyInjection.stop()
    }

}