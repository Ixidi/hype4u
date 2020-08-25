package xyz.ixidi.hype4u.spigot.framework.data

import org.bukkit.configuration.file.YamlConfiguration
import org.bukkit.plugin.java.JavaPlugin
import java.io.File

class PluginResourcesImpl(
    private val plugin: JavaPlugin
) : PluginResources {

    override fun getFile(name: String): File {
        val file = File(plugin.dataFolder, name)
        if (file.exists()) {
            return file
        }

        plugin.saveResource(name, true)
        return file
    }

    override fun getYaml(name: String): YamlConfiguration = YamlConfiguration.loadConfiguration(getFile(name))

}