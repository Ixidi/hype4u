package xyz.ixidi.hype4u.spigot.core.repository

import org.jetbrains.exposed.sql.Database
import java.util.*

class DatabaseUserGroupsRepository(
    private val database: Database
) : UserGroupsRepository {

    override fun getUserGroups(uuid: UUID): UserGroups {
        TODO("Not yet implemented")
    }

}