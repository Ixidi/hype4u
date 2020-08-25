package xyz.ixidi.hype4u.spigot.framework.repository

import java.io.File

interface PluginRepository {

    fun getLocalResourceFile(path: String): File?

}