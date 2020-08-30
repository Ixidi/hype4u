package xyz.ixidi.hype4u.core.user

import xyz.ixidi.hype4u.core.group.GroupManager
import xyz.ixidi.hype4u.core.group.UserGroups
import java.util.*

class UserImpl(
    override val uuid: UUID,
    override val name: String,
    groupManager: GroupManager
) : User {

    override val userGroups: UserGroups = UserGroups(uuid, groupManager)

    override fun hasPermission(permission: String): Boolean {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

}