package xyz.ixidi.hype4u.core.repository.userinfo

import org.jetbrains.exposed.sql.Database
import org.jetbrains.exposed.sql.transactions.transaction
import xyz.ixidi.hype4u.core.database.user.UserEntity
import xyz.ixidi.hype4u.core.database.user.UserTable
import java.util.*

class DatabaseUserInfoRepository(
    private val database: Database
) : UserInfoRepository {

    override fun loadUserInfo(uuid: UUID): UserInfo? = transaction(database) {
        val entity = UserEntity.find { UserTable.uuid eq uuid.toString() }.firstOrNull() ?: return@transaction null
        UserInfo(entity.name, UUID.fromString(entity.uuid))
    }

    override fun loadUserInfo(name: String): UserInfo? = transaction(database) {
        val entity = UserEntity.find { UserTable.name eq name }.firstOrNull() ?: return@transaction null
        UserInfo(entity.name, UUID.fromString(entity.uuid))
    }

}
