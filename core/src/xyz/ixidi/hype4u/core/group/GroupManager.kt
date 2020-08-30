package xyz.ixidi.hype4u.core.group

interface GroupManager {

    fun getAllGroups(): List<Group>

    fun getDefaultGroup(): Group
    fun setAsDefaultGroup(group: Group)

    fun getGroupByName(groupName: String): Group?

    fun createNewGroup(name: String): Group

}