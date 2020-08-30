package xyz.ixidi.hype4u.core.database.usergroup

import org.jetbrains.exposed.dao.id.IntIdTable

object UsersGroupsTable : IntIdTable("usersGroups") {

    val uuid = varchar("uuid", 36)
    val groupName = varchar("groupName", 50)
    val primary = bool("primary")

}