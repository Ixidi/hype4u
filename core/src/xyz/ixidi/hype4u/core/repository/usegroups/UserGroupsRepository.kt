package xyz.ixidi.hype4u.core.repository.usegroups

import java.util.*

interface UserGroupsRepository {

    fun getUserGroups(uuid: UUID): UserGroupsDTO
    fun saveUserGroups(userGroups: UserGroupsDTO)

}