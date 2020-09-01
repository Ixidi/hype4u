package xyz.ixidi.hype4u.core.repository.user

import org.bukkit.Server
import org.jetbrains.exposed.sql.Database
import org.jetbrains.exposed.sql.transactions.transaction
import xyz.ixidi.hype4u.core.database.user.UserEntity
import xyz.ixidi.hype4u.core.database.user.UserTable
import xyz.ixidi.hype4u.core.feature.group.GroupManager
import xyz.ixidi.hype4u.core.feature.punishment.PunishmentManager
import xyz.ixidi.hype4u.core.repository.usegroups.UserGroupsRepository
import xyz.ixidi.hype4u.core.user.User
import xyz.ixidi.hype4u.core.user.UserImpl
import java.util.*

class DatabaseUserRepository(
    private val database: Database,
    private val punishmentManager: PunishmentManager,
    private val userGroupsRepository: UserGroupsRepository,
    private val groupManager: GroupManager,
    private val server: Server
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