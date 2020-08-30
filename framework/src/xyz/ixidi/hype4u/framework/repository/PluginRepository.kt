package xyz.ixidi.hype4u.framework.repository

import java.io.File

interface PluginRepository {

    fun getLocalResourceFile(path: String): File?

}