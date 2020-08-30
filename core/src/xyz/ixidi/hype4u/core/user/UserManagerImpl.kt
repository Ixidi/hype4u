package xyz.ixidi.hype4u.core.user

import org.bukkit.entity.Player
import java.util.*
import kotlin.collections.ArrayList

class UserManagerImpl : UserManager {

    private val onlineUsers = ArrayList<User>()

    override fun getAllOnlineUsers(): List<User> = onlineUsers.toList()

    override fun getOnlineUser(player: Player): User = getOnlineUser(player.uniqueId)!!

    override fun getOnlineUser(uuid: UUID) = onlineUsers.firstOrNull { it.uuid == uuid }

    override fun addOnlineUser(user: User) {
        if (getOnlineUser(user.uuid) != null) return

        onlineUsers.add(user)
    }

    override fun removeOnlineUser(user: User) {
        onlineUsers.removeIf { it.uuid == user.uuid }
    }

}