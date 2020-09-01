package xyz.ixidi.hype4u.core.misc

import xyz.ixidi.hype4u.framework.language.TranslatableKey

enum class CoreTranslatableKey(
    override val key: String
): TranslatableKey {

    MESSAGE_COMMAND_BROADCAST("message.command.broadcast"),
    MESSAGE_PLAYER_NEVER_PLAYED("message.playerNeverPlayed"),
    MESSAGE_COMMAND_KICK_BYPASS("message.command.kick.bypass"),
    MESSAGE_COMMAND_KICK_SCREEN("message.command.kick.screen"),
    MESSAGE_COMMAND_KICK_SCREEN_REASON("message.command.kick.screenReason"),
    MESSAGE_COMMAND_KICK_BROADCAST("message.command.kick.broadcast"),
    MESSAGE_COMMAND_UNBAN_UNBANNED("message.command.unban.unbanned"),
    MESSAGE_COMMAND_UNBAN_NOT_BANNED("message.command.unban.notBanned"),
    MESSAGE_COMMAND_BAN_BYPASS("message.command.ban.bypass"),
    MESSAGE_COMMAND_BAN_BANNED("message.command.ban.banned"),
    MESSAGE_COMMAND_BAN_BROADCAST("message.command.ban.broadcast"),
    MESSAGE_COMMAND_BAN_SCREEN("message.command.ban.screen"),
    MESSAGE_COMMAND_BAN_SCREEN_REASON("message.command.ban.screenReason"),
    MESSAGE_COMMAND_TEMPBAN_BYPASS("message.command.tempban.bypass"),
    MESSAGE_COMMAND_TEMPBAN_BANNED("message.command.tempban.banned"),
    MESSAGE_COMMAND_TEMPBAN_BROADCAST("message.command.tempban.broadcast"),
    MESSAGE_COMMAND_TEMPBAN_SCREEN("message.command.tempban.screen"),
    MESSAGE_COMMAND_TEMPBAN_SCREEN_REASON("message.command.tempban.screenReason"),
    MESSAGE_CHAT_ENABLED_BROADCAST("message.command.chat.enabledBroadcast"),
    MESSAGE_CHAT_DISABLED_BROADCAST("message.command.chat.disabledBroadcast"),
    MESSAGE_CHAT_CLEARED_BROADCAST("message.command.chat.clearedBroadcast"),
    MESSAGE_CHAT_ALREADY_ENABLED("message.command.chat.alreadyEnabled"),
    MESSAGE_CHAT_ALREADY_DISABLED("message.command.chat.alreadyDisabled"),
    MESSAGE_CHAT_DISABLED("message.command.chat.disabled"),


}