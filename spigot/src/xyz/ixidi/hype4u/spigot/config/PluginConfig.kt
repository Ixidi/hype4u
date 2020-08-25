package xyz.ixidi.hype4u.spigot.config

import xyz.ixidi.hype4u.spigot.framework.repository.PluginRepository
import xyz.ixidi.hype4u.spigot.framework.config.AbstractConfiguration
import xyz.ixidi.hype4u.spigot.framework.config.ConfigException
import xyz.ixidi.hype4u.spigot.framework.language.Language

class PluginConfig(
    private val pluginRepository: PluginRepository
) : AbstractConfiguration({
    pluginRepository.getLocalResourceFile("config.yml") ?: throw Exception("Plugin does not contain config.yml.")
}) {

    val language by stringField("language", "en") {
        Language.values().firstOrNull { lang -> lang.code == it }
            ?: throw ConfigException("Language with code $it is not supported.")
    }

    val kickBroadcast by booleanField("kickBroadcast", true)

    val mysqlHost by stringField("mysql.host", "localhost")
    val mysqlUser by stringField("mysql.user", "root")
    val mysqlPassword by stringField("mysql.password", "")
    val mysqlPort by intField("mysql.port", 3306)
    val mysqlDatabase by stringField("mysql.database", "hypeplugin")

}