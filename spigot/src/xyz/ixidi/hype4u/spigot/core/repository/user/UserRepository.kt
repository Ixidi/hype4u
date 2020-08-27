package xyz.ixidi.hype4u.spigot.core.repository.user

import xyz.ixidi.hype4u.spigot.core.user.User
import java.util.*

interface UserRepository {

    fun loadUser(uuid: UUID): User
    fun saveUser(user: User)

}