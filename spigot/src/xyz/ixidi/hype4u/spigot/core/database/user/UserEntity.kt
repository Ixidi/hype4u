package xyz.ixidi.hype4u.spigot.core.database.user

import org.jetbrains.exposed.dao.IntEntity
import org.jetbrains.exposed.dao.IntEntityClass
import org.jetbrains.exposed.dao.id.EntityID

class UserEntity(id: EntityID<Int>) : IntEntity(id) {

    companion object : IntEntityClass<UserEntity>(UserTable)

    var uuid by UserTable.uuid
    var name by UserTable.name

}