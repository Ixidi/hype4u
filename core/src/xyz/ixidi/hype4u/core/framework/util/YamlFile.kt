package xyz.ixidi.hype4u.core.framework.util

import org.bukkit.configuration.file.YamlConfiguration
import java.io.File

class YamlFile(
    private val file: File
) : YamlConfiguration() {

    init {
        load()
    }

    fun load() {
        load(file)
    }

    fun save() {
        save(file)
    }

}