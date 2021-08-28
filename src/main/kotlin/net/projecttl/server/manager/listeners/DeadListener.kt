package net.projecttl.server.manager.listeners

import net.projecttl.server.manager.utils.FormatModule
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.entity.PlayerDeathEvent
import org.bukkit.plugin.java.JavaPlugin

class DeadListener(private val plugin: JavaPlugin) : Listener {

    private val message: String = plugin.config.getString("message.dead").toString()

    @EventHandler
    fun onDead(event: PlayerDeathEvent) {
        val player = event.entity
        val format = FormatModule(player, message)
        event.deathMessage = format.replacePlayer()
    }
}