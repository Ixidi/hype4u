package xyz.ixidi.hype4u.spigot.framework.task

import org.bukkit.scheduler.BukkitTask

interface TaskManager {

    fun runAsyncTask(run: () -> Unit): BukkitTask
    fun runSyncTask(run: () -> Unit): BukkitTask

}