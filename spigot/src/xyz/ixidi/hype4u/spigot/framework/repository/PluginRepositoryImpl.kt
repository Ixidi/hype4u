package xyz.ixidi.hype4u.spigot.framework.repository

import org.bukkit.plugin.Plugin
import java.io.File

class PluginRepositoryImpl(
    private val plugin: Plugin
) : PluginRepository {

    override fun getLocalResourceFile(path: String): File? {
        val file = File(plugin.dataFolder, path)
        if (!file.exists()) {
            file.parentFile.mkdirs()
            try {
                plugin.saveResource(path, true)
            } catch (ex: IllegalStateException) {
                return null
            }
        }

        return file
    }

}