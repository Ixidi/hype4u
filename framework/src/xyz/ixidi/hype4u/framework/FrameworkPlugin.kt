package xyz.ixidi.hype4u.framework

import org.bukkit.plugin.java.JavaPlugin
import org.koin.core.get
import xyz.ixidi.hype4u.framework.message.Messages
import xyz.ixidi.hype4u.framework.injection.FrameworkDependencyInjection
import xyz.ixidi.hype4u.framework.injection.frameworkModule

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