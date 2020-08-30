package xyz.ixidi.hype4u.core.misc

import xyz.ixidi.hype4u.framework.language.TranslatableKey

enum class CoreTranslatableKey(
    override val key: String
): TranslatableKey {

    MESSAGE_COMMAND_KICK_BYPASS("message.command.kick.bypass"),
    MESSAGE_COMMAND_KICK_SCREEN("message.command.kick.screen"),
    MESSAGE_COMMAND_KICK_SCREEN_REASON("message.command.kick.screenReason"),
    MESSAGE_COMMAND_KICK_BROADCAST("message.command.kick.broadcast"),
    MESSAGE_COMMAND_BAN_BYPASS("message.command.ban.bypass"),
    MESSAGE_COMMAND_BAN_BROADCAST("message.command.ban.broadcast"),
    MESSAGE_COMMAND_BAN_SCREEN("message.command.ban.screen"),
    MESSAGE_COMMAND_BAN_SCREEN_REASON("message.command.ban.screenReason"),
    /*MESSAGE_COMMAND_TEMPBAN_BYPASS("message.command.tempban.bypass"),
    MESSAGE_COMMAND_TEMPBAN_BROADCAST("message.command.tempban.broadcast"),
    MESSAGE_COMMAND_TEMPBAN_SCREEN("message.command.tempban.screen"),
    MESSAGE_COMMAND_TEMPBAN_SCREEN_REASON("message.command.tempban.screenReason")*/


}