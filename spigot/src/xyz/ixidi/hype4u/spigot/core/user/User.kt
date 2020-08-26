package xyz.ixidi.hype4u.spigot.core.user

import xyz.ixidi.hype4u.spigot.core.group.Group
import java.util.*

interface User {

    val uuid: UUID
    val isBanned: Boolean
    val primaryGroup: Group
    val secondaryGroups: List<Group>

    fun setPrimaryGroup(group: Group)
    fun addSecondaryGroup(group: Group)
    fun removeSecondaryGroup(group: Group)

}