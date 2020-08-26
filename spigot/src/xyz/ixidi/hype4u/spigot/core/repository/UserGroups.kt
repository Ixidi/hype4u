package xyz.ixidi.hype4u.spigot.core.repository

import xyz.ixidi.hype4u.spigot.core.group.Group
import java.util.*

data class UserGroups(
    val uuid: UUID,
    val primary: Group,
    val secondary: List<Group>
)