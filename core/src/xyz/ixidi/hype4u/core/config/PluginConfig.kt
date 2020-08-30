package xyz.ixidi.hype4u.core.config

import xyz.ixidi.hype4u.framework.repository.PluginRepository
import xyz.ixidi.hype4u.framework.config.AbstractConfiguration

class PluginConfig(
    private val pluginRepository: PluginRepository
) : AbstractConfiguration({
    pluginRepository.getLocalResourceFile("config.yml") ?: throw Exception("Plugin does not contain config.yml.")
}) {

    val kickBroadcast by booleanField("kickBroadcast", true)
    val banBroadcast by booleanField("banBroadcast", true)
}