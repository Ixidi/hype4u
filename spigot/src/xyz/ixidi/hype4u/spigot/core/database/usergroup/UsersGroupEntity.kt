package xyz.ixidi.hype4u.spigot.core.database.usergroup

import org.jetbrains.exposed.dao.IntEntity
import org.jetbrains.exposed.dao.IntEntityClass
import org.jetbrains.exposed.dao.id.EntityID

class UsersGroupEntity(id: EntityID<Int>) : IntEntity(id) {

    companion object : IntEntityClass<UsersGroupEntity>(UsersGroupsTable)

    var uuid by UsersGroupsTable.uuid
    var groupName by UsersGroupsTable.groupName
    var primary by UsersGroupsTable.primary

}