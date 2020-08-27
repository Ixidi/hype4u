package xyz.ixidi.hype4u.spigot.core.repository.usegroups

import java.util.*

data class UserGroupsDTO(
    val uuid: UUID,
    val primary: String,
    val secondary: List<String>
)