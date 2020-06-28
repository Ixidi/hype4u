package xyz.ixidi.hype4u.core.permission

object CoreLangKeys {

    private fun c(text: String) = "core.$text"

    val GROUPS_MANAGEMENT_GUI_ITEM_TITLE = c("gui.panel.groupsManagement")
    val SELECT_GROUP_GUI_TITLE = c("gui.selectGroup.title")

    val PLAYER_OFFLINE_MESSAGE = c("message.playerOffline")
    val PERMISSION_MESSAGE = c("message.permission")
    val CANNOT_EXECUTE_COMMAND_MESSAGE = c("message.command.cannotExecute")
    val USAGE_COMMAND_MESSAGE = c("message.command.usage")
    val ARGUMENT_HANDLER_ERROR_MESSAGE = c("message.command.argumentHandlerError")
    val ERROR_DOUBLE_COMMAND_MESSAGE = c("message.command.errorDouble")
    val ERROR_POSITIVE_DOUBLE_COMMAND_MESSAGE = c("message.command.errorPositiveDouble")
    val ERROR_INTEGER_COMMAND_MESSAGE = c("message.command.errorInteger")
    val ERROR_POSITIVE_INTEGER_COMMAND_MESSAGE = c("message.command.errorPositiveInteger")

    val GROUPS_DISPLAY = c("message.groupsDisplay")
    val GROUP_PERMISSIONS_DISPLAY = c("message.groupPermissionsDisplay")
    val GROUP_NOT_EXIST = c("message.groupNotExist")
    val GROUP_EXISTS_MESSAGE = c("message.groupExists")
    val GROUP_CREATED_MESSAGE = c("message.groupCreated")
    val GROUP_HAS_BEEN_REMOVED_MESSAGE = c("message.groupHasBeenRemoved")

}