package xyz.ixidi.hype4u.core.user

import xyz.ixidi.hype4u.core.group.UserGroups
import java.util.*

interface User {

    val uuid: UUID
    val name: String
    val userGroups: UserGroups

    fun hasPermission(permission: String): Boolean

}