package xyz.ixidi.hype4u.core.framework.command

import org.bukkit.command.CommandSender
import org.bukkit.entity.Player
import xyz.ixidi.hype4u.core.framework.command.rich.RichCommand

inline fun <reified ARGUMENTS_MODEL : Any> richCommand(noinline onExecute: ARGUMENTS_MODEL.(sender: CommandSender) -> Unit)
        = RichCommand(ARGUMENTS_MODEL::class, CommandSender::class, onExecute)

inline fun <reified ARGUMENTS_MODEL : Any> richPlayerCommand(noinline onExecute: ARGUMENTS_MODEL.(sender: Player) -> Unit)
        = RichCommand(ARGUMENTS_MODEL::class, Player::class, onExecute)
