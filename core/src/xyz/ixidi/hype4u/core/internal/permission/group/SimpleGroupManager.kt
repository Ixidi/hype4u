package xyz.ixidi.hype4u.core.internal.permission.group

import org.bukkit.plugin.Plugin
import xyz.ixidi.hype4u.core.framework.permission.group.Group
import xyz.ixidi.hype4u.core.framework.permission.group.GroupManager
import java.util.*
import kotlin.collections.ArrayList
import kotlin.collections.HashMap

internal class SimpleGroupManager(
    private val plugin: Plugin
) : GroupManager {

    class GroupNotRegisteredException : Exception()
    class NotMemberOfGroupException : Exception()
    class GroupAlreadyExistsException : Exception()

    private val groupList = ArrayList<Group>()
    private val primaryGroupMap = HashMap<UUID, Group>()
    private var defaultGroup = createNewGroup("default").apply { isRoot = true }

    override fun getDefaultGroup(): Group = defaultGroup

    override fun setAsDefaultGroup(group: Group) {
        if (!groupList.contains(group)) throw GroupNotRegisteredException()
        defaultGroup = group
    }

    override fun getGroupByName(groupName: String): Group? = groupList.firstOrNull { it.name == groupName }

    override fun isPrimaryGroup(uuid: UUID, group: Group): Boolean = primaryGroupMap[uuid] == group

    override fun setAsPrimaryGroup(uuid: UUID, group: Group) {
        if (!groupList.contains(group)) throw GroupNotRegisteredException()
        if (!group.isMember(uuid)) throw NotMemberOfGroupException()

        primaryGroupMap[uuid] = group
    }

    override fun getAllGroups(): List<Group> = groupList.toList()

    override fun getPrimaryGroupByUUID(uuid: UUID): Group? = primaryGroupMap[uuid]

    override fun getSecondaryGroupsByUUUI(uuid: UUID): List<Group> {
        val primary = primaryGroupMap[uuid] ?: return emptyList()
        return groupList.filter { it.isMember(uuid) && it != primary }
    }

    override fun getAllGroupsByUUUI(uuid: UUID): List<Group> {
        return groupList.filter { it.isMember(uuid) }
    }

    override fun createNewGroup(name: String): Group =
        SimpleGroup(name, this, plugin).apply {
            if (groupList.any { it.name == name }) throw GroupAlreadyExistsException()

            groupList.add(this)
        }

}
