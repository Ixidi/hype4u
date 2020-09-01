package xyz.ixidi.hype4u.framework.task

import org.bukkit.plugin.Plugin
import org.bukkit.scheduler.BukkitTask

internal class TaskManagerImpl(
    private val plugin: Plugin
) : TaskManager {

    override fun <T> runAsyncWithSyncCallback(run: () -> T, callback: (T) -> Unit, error: (Exception) -> Unit) {
        runAsyncTask {
            val t = try {
                run()
            } catch (ex: Exception) {
                runSyncTask {
                    error(ex)
                }
                return@runAsyncTask
            }

            runSyncTask {
                callback(t)
            }
        }
    }

    override fun runAsyncTask(run: () -> Unit): BukkitTask = plugin.server.scheduler.runTaskAsynchronously(plugin, run)
    override fun runSyncTask(run: () -> Unit): BukkitTask = plugin.server.scheduler.runTask(plugin, run)

}