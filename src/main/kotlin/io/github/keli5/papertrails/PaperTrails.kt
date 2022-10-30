package io.github.keli5.papertrails

import io.github.keli5.papertrails.command.CommandPreviewTrail
import org.bukkit.Bukkit
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.player.PlayerJoinEvent
import org.bukkit.plugin.java.JavaPlugin

val nameToCommandExecutor = mapOf(
    "previewtrail" to CommandPreviewTrail(),
)

class PaperTrails : JavaPlugin(), Listener {
    override fun onEnable() {
        Bukkit.getPluginManager().registerEvents(this, this)

        for ((name, executor) in nameToCommandExecutor) {
            getCommand(name)!!.setExecutor(executor)
        }
    }

    @EventHandler
    fun onPlayerJoin(event: PlayerJoinEvent) {
        // pass
    }

}