package xyz.ixidi.hype4u.core.internal.permission.group

import org.bukkit.command.CommandSender
import xyz.ixidi.hype4u.core.framework.command.rich.argument.handler.ArgumentHandler
import xyz.ixidi.hype4u.core.framework.permission.group.Group
import xyz.ixidi.hype4u.core.framework.permission.group.GroupManager
import xyz.ixidi.hype4u.core.permission.CoreLangKeys

internal class GroupArgumentHandler(
    private val groupManager: GroupManager
) : ArgumentHandler<Group>() {

    override fun handle(sender: CommandSender, argumentName: String, string: String): Group =
        groupManager.getGroupByName(string) ?: throw argumentError(CoreLangKeys.GROUP_NOT_EXIST, "group" to string)

    override fun tabComplete(sender: CommandSender, argumentName: String, string: String): List<String> =
        groupManager.getAllGroups().filter { it.name.startsWith(string) }.map { it.name }

}