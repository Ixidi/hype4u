package xyz.ixidi.hype4u.framework

import xyz.ixidi.hype4u.framework.language.TranslatableKey

internal enum class FrameworkTranslatableKey(
    override val key: String
) : TranslatableKey {

    MESSAGE_PLAYER_OFFLINE("message.playerOffline"),
    MESSAGE_UNKNOWN_TIME_UNIT("message.unknownTimeUnit"),
    MESSAGE_UNKNOWN_TIME_FORMAT("message.unknownTimeFormat"),
    MESSAGE_COMMAND_NO_PERMISSION("message.command.noPermission"),
    MESSAGE_COMMAND_SENDER_LEVEL("message.command.senderLevel"),
    MESSAGE_COMMAND_MISSING_PARAMETER("message.command.missingParameter"),
    MESSAGE_COMMAND_UNKNOWN_ERROR("message.command.unknownError"),
    MESSAGE_COMMAND_HELP_HEADER("message.command.help.header"),
    MESSAGE_COMMAND_HELP_ENTRY("message.command.help.entry"),
    MESSAGE_COMMAND_SUBCOMMAND_NOT_EXIST("message.command.subCommandNotExist"),
    MESSAGE_RELOAD("message.reload")

}