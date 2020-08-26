package xyz.ixidi.hype4u.spigot.core.repository

import java.util.*

interface UserGroupsRepository {

    fun getUserGroups(uuid: UUID): UserGroups

}