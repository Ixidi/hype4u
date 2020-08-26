package xyz.ixidi.hype4u.spigot.config.io

import org.bukkit.configuration.ConfigurationSection
import xyz.ixidi.hype4u.spigot.config.dto.GroupDTO
import xyz.ixidi.hype4u.spigot.framework.config.ConfigException
import xyz.ixidi.hype4u.spigot.framework.config.field.io.FieldIO

object GroupDTOFieldIO : FieldIO<GroupDTO> {

    override fun ConfigurationSection.load(key: String): GroupDTO? {
        val name = getString("name") ?: throw ConfigException("name field is missing.")
        val default = getBoolean("default")
        val displayNameFormat = getString("displayNameFormat") ?: throw ConfigException("displayNameFormat field is missing.")
        val chatFormat = getString("chatFormat") ?: throw ConfigException("chatFormat field is missing.")

        val section = getConfigurationSection("permissions") ?: throw ConfigException("permissions section is missing.")
        val permissions = HashMap<String, Boolean>()
        section.getKeys(true).forEach {
            permissions[it] = section.getBoolean(it)
        }

        return GroupDTO(name, default, displayNameFormat, chatFormat, permissions)
    }

    override fun ConfigurationSection.save(key: String, value: GroupDTO) {
        TODO()
    }


}