package xyz.ixidi.hype4u.spigot.core.database.user

import org.jetbrains.exposed.dao.id.IntIdTable

object UserTable : IntIdTable("users") {

    val uuid = varchar("uuid", 36)
    val name = varchar("name", 16)

}