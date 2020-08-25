package xyz.ixidi.hype4u.spigot.core.punishment

import java.util.*

class Punishment(
    val active: Boolean,
    val type: PunishmentType,
    val target: String,
    val targetUUID: UUID,
    val executor: String,
    val executorUUID: UUID,
    val date: Date,
    val reason: String,
    val expiresAt: Date?
)