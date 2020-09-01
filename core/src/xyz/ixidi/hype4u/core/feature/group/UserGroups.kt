package xyz.ixidi.hype4u.core.feature.group

import java.util.*
import kotlin.collections.ArrayList

class UserGroups(
    val uuid: UUID,
    private val groupManager: GroupManager
) {

    class GroupNotRegisteredException(val group: Group) : Exception("Group ${group.name} is not registered.")

    private var primaryGroup = groupManager.getDefaultGroup()
    private val secondaryGroups = ArrayList<Group>()

    fun primaryGroup() = primaryGroup
    fun secondaryGroups() = secondaryGroups.toList()

    fun setPrimaryGroup(group: Group) {
        if (!groupManager.getAllGroups().contains(group)) {
            throw GroupNotRegisteredException(group)
        }

        if (primaryGroup == group) return

        if (secondaryGroups.contains(group)) {
            secondaryGroups.remove(group)
        }

        primaryGroup = group
    }

    fun addSecondaryGroup(group: Group) {
        if (!groupManager.getAllGroups().contains(group)) {
            throw GroupNotRegisteredException(group)
        }

        if (secondaryGroups.contains(group)) return
        if (primaryGroup == group) return

        secondaryGroups.add(group)
    }

}