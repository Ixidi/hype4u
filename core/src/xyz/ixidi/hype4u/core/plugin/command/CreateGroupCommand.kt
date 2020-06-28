package xyz.ixidi.hype4u.core.plugin.command

import xyz.ixidi.hype4u.core.framework.command.rich.argument.Argument
import xyz.ixidi.hype4u.core.framework.command.rich.argument.handler.primitive.StringArgumentHandler
import xyz.ixidi.hype4u.core.framework.command.richCommand
import xyz.ixidi.hype4u.core.framework.permission.group.GroupManager
import xyz.ixidi.hype4u.core.framework.util.message
import xyz.ixidi.hype4u.core.permission.CoreLangKeys

internal data class CreateGroupCommandArgumentModel(
    @Argument(StringArgumentHandler::class, "nazwa grupy")
    val groupName: String
)

internal fun createGroupCommand(groupManager: GroupManager) = richCommand<CreateGroupCommandArgumentModel> c@{
    if (groupManager.getGroupByName(groupName) != null) {
        it.message(CoreLangKeys.GROUP_EXISTS_MESSAGE)
        return@c
    }

    val group = groupManager.createNewGroup(groupName)
    it.message(CoreLangKeys.GROUP_CREATED_MESSAGE, "name" to group.name)
}