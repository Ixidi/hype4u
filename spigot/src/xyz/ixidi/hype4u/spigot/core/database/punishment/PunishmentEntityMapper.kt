package xyz.ixidi.hype4u.spigot.core.database.punishment

import xyz.ixidi.hype4u.spigot.core.punishment.*
import xyz.ixidi.hype4u.spigot.framework.util.Mapper
import java.util.*

object PunishmentEntityMapper : Mapper<PunishmentEntity, Punishment> {

    override fun PunishmentEntity.map(): Punishment = Punishment(
        active = active,
        type = type,
        target = target,
        targetUUID = targetUUID,
        executor = executor,
        executorUUID = executorUUID,
        date = Date(date),
        reason = reason,
        expiresAt = expires?.let { Date(it) }
    )

}