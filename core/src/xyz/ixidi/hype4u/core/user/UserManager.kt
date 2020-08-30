package xyz.ixidi.hype4u.core.user

import org.bukkit.entity.Player
import java.util.*

interface UserManager {

    fun getAllOnlineUsers(): List<User>
    fun getOnlineUser(player: Player): User
    fun getOnlineUser(uuid: UUID): User?
    fun addOnlineUser(user: User)
    fun removeOnlineUser(user: User)

}