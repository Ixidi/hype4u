package xyz.ixidi.hype4u.spigot.misc

enum class TranslatableKey(
    val key: String
) {

    MESSAGE_PLAYER_OFFLINE("message.playerOffline"),
    MESSAGE_COMMAND_NO_PERMISSION("message.command.noPermission"),
    MESSAGE_COMMAND_SENDER_LEVEL("message.command.senderLevel"),
    MESSAGE_COMMAND_MISSING_PARAMETER("message.command.missingParameter"),
    MESSAGE_COMMAND_UNKNOWN_ERROR("message.command.unknownError"),
    MESSAGE_COMMAND_KICK_BYPASS("message.command.kick.bypass"),
    MESSAGE_COMMAND_KICK_SCREEN("message.command.kick.screen"),
    MESSAGE_COMMAND_KICK_SCREEN_REASON("message.command.kick.screenReason"),
    MESSAGE_COMMAND_KICK_BROADCAST("message.command.kick.broadcast"),
    MESSAGE_COMMAND_BAN_BYPASS("message.command.ban.bypass"),
    MESSAGE_COMMAND_BAN_BROADCAST("message.command.ban.broadcast"),
    MESSAGE_COMMAND_BAN_SCREEN("message.command.ban.screen"),
    MESSAGE_COMMAND_BAN_SCREEN_REASON("message.command.ban.screenReason"),
    MESSAGE_COMMAND_TEMPBAN_BYPASS("message.command.tempban.bypass"),
    MESSAGE_COMMAND_TEMPBAN_BROADCAST("message.command.tempban.broadcast"),
    MESSAGE_COMMAND_TEMPBAN_SCREEN("message.command.tempban.screen"),
    MESSAGE_COMMAND_TEMPBAN_SCREEN_REASON("message.command.tempban.screenReason")


}