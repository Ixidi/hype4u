package xyz.ixidi.hype4u.spigot.core.repository.usegroups

import org.jetbrains.exposed.sql.Database
import org.jetbrains.exposed.sql.transactions.transaction
import xyz.ixidi.hype4u.spigot.core.database.usergroup.UsersGroupEntity
import xyz.ixidi.hype4u.spigot.core.database.usergroup.UsersGroupsTable
import xyz.ixidi.hype4u.spigot.core.group.GroupManager
import java.util.*
import java.util.logging.Logger
import kotlin.collections.ArrayList

class DatabaseUserGroupsRepository(
    private val database: Database,
    private val groupManager: GroupManager
) : UserGroupsRepository {

    private companion object val LOGGER = Logger.getLogger("DatabaseUserGroupsRepository")

    override fun getUserGroups(uuid: UUID): UserGroupsDTO = transaction(database) {
        val entities = UsersGroupEntity.find { UsersGroupsTable.uuid eq uuid.toString() }
        if (entities.count() == 0L) {
            UserGroupsDTO(uuid, groupManager.getDefaultGroup().name, emptyList()).also {
                UsersGroupEntity.new {
                    this.uuid = uuid.toString()
                    groupName = it.primary
                    primary = true
                }
            }
        }

        var primary: String? = null
        val secondary = ArrayList<String>()

        for (entity in entities) {
            if (primary == entity.groupName || secondary.contains(entity.groupName)) {
                entity.delete()
                continue
            }

            if (entity.primary) {
                if (primary == null) {
                    primary = entity.groupName
                } else {
                    secondary.add(entity.groupName)
                    entity.primary = false
                }
                continue
            }

            secondary.add(entity.groupName)
        }

        if (primary == null && secondary.isNotEmpty()) {
            primary = secondary.removeAt(0)
        }

        UserGroupsDTO(uuid, primary ?: groupManager.getDefaultGroup().name, secondary)
    }

    override fun saveUserGroups(userGroups: UserGroupsDTO) {
        val stringUUID = userGroups.uuid.toString()
        transaction(database) {
            UsersGroupEntity.find { UsersGroupsTable.uuid eq stringUUID }.forEach { it.delete() }
            UsersGroupEntity.new {
                uuid = stringUUID
                groupName = userGroups.primary
                primary = true
            }
            userGroups.secondary.forEach {
                UsersGroupEntity.new {
                    uuid = stringUUID
                    groupName = it
                    primary = false
                }
            }
        }
    }
}