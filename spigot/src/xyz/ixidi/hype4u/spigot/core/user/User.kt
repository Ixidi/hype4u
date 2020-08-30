package xyz.ixidi.hype4u.spigot.core.user

import xyz.ixidi.hype4u.spigot.core.group.UserGroups
import xyz.ixidi.hype4u.spigot.core.punishment.Punishment
import java.util.*

interface User {

    val uuid: UUID
    val name: String
    val userGroups: UserGroups
    val activePunishments: List<Punishment>

    fun hasPermission(permission: String): Boolean
}