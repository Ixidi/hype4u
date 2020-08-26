package xyz.ixidi.hype4u.spigot.core.database

import org.jetbrains.exposed.dao.id.IntIdTable

object UsersGroupsTable : IntIdTable("usersGroups") {

    val uuid = uuid("uuid")
    val groupName = varchar("groupName", 50)
    val default = bool("default")

}