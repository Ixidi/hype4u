package xyz.ixidi.hype4u.core.command

import org.bukkit.Server
import org.bukkit.command.CommandSender
import xyz.ixidi.hype4u.core.misc.CoreTranslatableKey
import xyz.ixidi.hype4u.core.misc.Permission
import xyz.ixidi.hype4u.framework.command.annotation.*
import xyz.ixidi.hype4u.framework.message.Messages

@SingleCommandsContainer
class BroadcastCommand(
    private val server: Server,
    private val messages: Messages
) {

    @Command(
        name = "broadcast",
        desc = "Wysyła ogłoszenie na chacie.",
        aliases = ["bc"],
        permission = Permission.COMMAND_BROADCAST
    )
    private fun broadcast(
        @Sender sender: CommandSender,
        @Arg("wiadomość", "Wiadomość, która ma zostać wysłana.") @GreedyString message: String
    ) {
        server.broadcastMessage(messages.getColoredMessage(CoreTranslatableKey.MESSAGE_COMMAND_BROADCAST, "message" to message))
    }

}