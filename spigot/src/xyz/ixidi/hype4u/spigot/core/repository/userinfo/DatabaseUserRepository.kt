package xyz.ixidi.hype4u.spigot.core.repository.userinfo

import org.bukkit.Server
import org.jetbrains.exposed.sql.Database
import org.jetbrains.exposed.sql.transactions.transaction
import xyz.ixidi.hype4u.spigot.core.database.user.UserEntity
import xyz.ixidi.hype4u.spigot.core.database.user.UserTable
import xyz.ixidi.hype4u.spigot.core.group.GroupManager
import xyz.ixidi.hype4u.spigot.core.punishment.PunishmentManager
import xyz.ixidi.hype4u.spigot.core.repository.usegroups.UserGroupsRepository
import xyz.ixidi.hype4u.spigot.core.user.User
import xyz.ixidi.hype4u.spigot.core.user.UserImpl
import java.util.*

class DatabaseUserRepository(
    private val database: Database,
    private val server: Server,
    private val punishmentManager: PunishmentManager,
    private val userGroupsRepository: UserGroupsRepository,
    private val groupManager: GroupManager
) : UserRepository {

    override fun loadUser(uuid: UUID): User = transaction(database) {
        val entity = UserEntity.find { UserTable.uuid eq uuid.toString() }.firstOrNull()
        val player = server.getPlayer(uuid)

        punishmentManager.load(uuid)
        val user = UserImpl(uuid, player?.name ?: entity?.name ?: "-", groupManager)
        userGroupsRepository.getUserGroups(uuid).run {
            val userGroups = user.userGroups
            userGroups.setPrimaryGroup(groupManager.getGroupByName(primary) ?: groupManager.getDefaultGroup())

            for (s in secondary) {
                val g = groupManager.getGroupByName(s) ?: continue
                userGroups.addSecondaryGroup(g)
            }
        }

        if (entity == null) {
            saveUser(user)
        }

        user
    }

    override fun saveUser(user: User) {
        transaction(database) {
            punishmentManager.save(user.uuid)
            val entity = UserEntity.find { UserTable.uuid eq user.uuid.toString() }.firstOrNull()
            if (entity == null) {
                UserEntity.new {
                    name = user.name
                    uuid = user.uuid.toString()
                }
            } else {
                entity.name = server.getPlayer(user.uuid)?.name ?: user.name
            }

            return@transaction
        }
    }


}