package xyz.ixidi.hype4u.framework.plugin

import org.bukkit.plugin.java.JavaPlugin
import org.koin.core.KoinComponent
import org.koin.core.get
import org.koin.core.module.Module
import xyz.ixidi.hype4u.framework.Commands
import xyz.ixidi.hype4u.framework.Listeners
import xyz.ixidi.hype4u.framework.injection.DependencyInjection
import xyz.ixidi.hype4u.framework.language.TranslationManager

abstract class HypePlugin<out T : PluginApi>(
    private val pluginInfo: PluginInfo = SimplePluginInfo()
) : JavaPlugin() {

    private lateinit var dependencyInjection: DependencyInjection

    final override fun onEnable() {
        dependencyInjection = DependencyInjection()
        dependencyInjection.start(getKoinModules().plus(externalPluginModule(this@HypePlugin, pluginInfo)))
        dependencyInjection.get<Commands>().registerAll() //TODO
        dependencyInjection.get<Listeners>().registerAll() //TODO
        dependencyInjection.get<TranslationManager>().load(pluginInfo.translatableKeys, dependencyInjection.get())
        onPluginEnable(dependencyInjection)
    }

    final override fun onDisable() {
        onPluginDisable(dependencyInjection)
        dependencyInjection.stop()
    }

    protected open fun onPluginEnable(koin: KoinComponent) {}
    protected open fun onPluginDisable(koin: KoinComponent) {}

    protected abstract fun getPublicApi(): T
    protected abstract fun getKoinModules(): List<Module>

}