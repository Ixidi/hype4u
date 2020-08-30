package xyz.ixidi.hype4u.misc

import xyz.ixidi.hype4u.framework.language.TranslatableKey

internal enum class FrameworkTranslatableKey(
    override val key: String
) : TranslatableKey {

    MESSAGE_PLAYER_OFFLINE("message.playerOffline"),
    MESSAGE_COMMAND_NO_PERMISSION("message.command.noPermission"),
    MESSAGE_COMMAND_SENDER_LEVEL("message.command.senderLevel"),
    MESSAGE_COMMAND_MISSING_PARAMETER("message.command.missingParameter"),
    MESSAGE_COMMAND_UNKNOWN_ERROR("message.command.unknownError"),
    MESSAGE_RELOAD("message.reload")

}