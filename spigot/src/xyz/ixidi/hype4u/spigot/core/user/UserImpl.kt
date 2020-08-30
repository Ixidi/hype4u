package xyz.ixidi.hype4u.spigot.core.user

import xyz.ixidi.hype4u.spigot.core.group.GroupManager
import xyz.ixidi.hype4u.spigot.core.group.UserGroups
import xyz.ixidi.hype4u.spigot.core.punishment.Punishment
import xyz.ixidi.hype4u.spigot.core.punishment.PunishmentManager
import java.util.*

class UserImpl(
    override val uuid: UUID,
    override val name: String,
    private val punishmentManager: PunishmentManager,
    groupManager: GroupManager
) : User {

    override val userGroups: UserGroups = UserGroups(uuid, groupManager)
    override val activePunishments: List<Punishment>
        get() = punishmentManager.getActivePunishments(uuid)

    override fun hasPermission(permission: String): Boolean {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}