package xyz.ixidi.hype4u.core.framework.command

import org.bukkit.command.CommandSender
import org.bukkit.entity.Player
import xyz.ixidi.hype4u.core.framework.util.message
import xyz.ixidi.hype4u.core.permission.CoreLangKeys

fun baseCommand(init: (sender: CommandSender, args: List<String>) -> Unit) =
    command(onExecute = { sender, args ->
        init(sender, args)
    })

fun playerCommand(init: (player: Player, args: List<String>) -> Unit) =
    command(onExecute = c@{ sender, args ->
        if (sender !is Player) {
            sender.message(CoreLangKeys.CANNOT_EXECUTE_COMMAND_MESSAGE)
            return@c
        }

        init(sender, args.toList())
    })
