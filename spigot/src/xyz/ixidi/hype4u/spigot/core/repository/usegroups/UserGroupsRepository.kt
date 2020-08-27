package xyz.ixidi.hype4u.spigot.core.repository.usegroups

import java.util.*

interface UserGroupsRepository {

    fun getUserGroups(uuid: UUID): UserGroupsDTO
    fun saveUserGroups(userGroups: UserGroupsDTO)

}