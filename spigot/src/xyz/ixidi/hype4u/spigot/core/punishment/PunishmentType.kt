package xyz.ixidi.hype4u.spigot.core.punishment

enum class PunishmentType(val typeName: String) {

    KICK("kick"),
    PERMANENT_BAN("permanentBan"),
    TEMPORARY_BAN("temporaryBan"),
    BAN_REVOCATION("banRevocation")

}