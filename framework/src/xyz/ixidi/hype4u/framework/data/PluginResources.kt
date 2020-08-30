package xyz.ixidi.hype4u.framework.data

import org.bukkit.configuration.file.YamlConfiguration
import java.io.File

interface PluginResources {

    fun getFile(name: String): File
    fun getYaml(name: String): YamlConfiguration

}