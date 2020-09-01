package xyz.ixidi.hype4u.framework.task

import org.bukkit.scheduler.BukkitTask

interface TaskManager {

    fun <T> runAsyncWithSyncCallback(run: () -> T, callback: (T) -> Unit, error: (Exception) -> Unit)
    fun runAsyncTask(run: () -> Unit): BukkitTask
    fun runSyncTask(run: () -> Unit): BukkitTask

}