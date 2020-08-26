package xyz.ixidi.hype4u.spigot.framework.task

import org.bukkit.plugin.Plugin
import org.bukkit.scheduler.BukkitTask

class TaskManagerImpl(
    private val plugin: Plugin
) : TaskManager {

    override fun runAsyncTask(run: () -> Unit): BukkitTask = plugin.server.scheduler.runTaskAsynchronously(plugin, run)
    override fun runSyncTask(run: () -> Unit): BukkitTask = plugin.server.scheduler.runTask(plugin, run)

}