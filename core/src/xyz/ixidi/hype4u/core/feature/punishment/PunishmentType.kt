package xyz.ixidi.hype4u.core.feature.punishment

enum class PunishmentType(val typeName: String) {

    KICK("kick"),
    PERMANENT_BAN("permanentBan"),
    TEMPORARY_BAN("temporaryBan"),
    UNBAN("unban"),
    UNKNOWN("")

}