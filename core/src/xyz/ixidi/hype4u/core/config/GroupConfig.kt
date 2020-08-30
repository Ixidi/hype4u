package xyz.ixidi.hype4u.core.config

import xyz.ixidi.hype4u.core.config.io.GroupDTOFieldIO
import xyz.ixidi.hype4u.framework.config.AbstractConfiguration
import xyz.ixidi.hype4u.framework.repository.PluginRepository

class GroupConfig(
    private val pluginRepository: PluginRepository
) : AbstractConfiguration({
    pluginRepository.getLocalResourceFile("groups.yml") ?: throw Exception("Plugin does not contain groups.yml.")
}) {

    val groups by mapField(GroupDTOFieldIO, "groups", emptyMap())

}