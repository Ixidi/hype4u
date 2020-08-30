package xyz.ixidi.hype4u.core.config.io

import org.bukkit.configuration.ConfigurationSection
import xyz.ixidi.hype4u.config.dto.GroupDTO
import xyz.ixidi.hype4u.framework.config.ConfigException
import xyz.ixidi.hype4u.framework.config.field.io.FieldIO

object GroupDTOFieldIO : FieldIO<GroupDTO> {

    override fun ConfigurationSection.load(key: String): GroupDTO? {
        val default = getBoolean("default")
        val displayNameFormat = getString("displayNameFormat") ?: throw ConfigException("displayNameFormat field is missing.")
        val chatFormat = getString("chatFormat") ?: throw ConfigException("chatFormat field is missing.")

        val section = getConfigurationSection("permissions") ?: throw ConfigException("permissions section is missing.")
        val permissions = HashMap<String, Boolean>()
        section.getKeys(true).forEach {
            permissions[it] = section.getBoolean(it)
        }

        return GroupDTO(key, default, displayNameFormat, chatFormat, permissions)
    }

    override fun ConfigurationSection.save(key: String, value: GroupDTO) {
        TODO()
    }


}