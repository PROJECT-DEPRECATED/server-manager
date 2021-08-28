package net.projecttl.server.manager.listeners

import net.projecttl.server.manager.utils.FormatModule
import net.projecttl.server.manager.utils.PlayerList
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.player.PlayerJoinEvent
import org.bukkit.event.player.PlayerQuitEvent
import org.bukkit.plugin.java.JavaPlugin

class JoinQuitListener(private val plugin: JavaPlugin) : Listener {

    private val firstJoinMessage: String = plugin.config.getString("message.firstJoin").toString()
    private val joinMessage: String      = plugin.config.getString("message.join").toString()
    private val quitMessage: String      = plugin.config.getString("message.quit").toString()

    @EventHandler
    fun onJoin(event: PlayerJoinEvent) {
        val list = PlayerList()
        if (!list.queryPlayer().contains(event.player.name)) {
            list.addPlayer(event.player)

            val formatModule = FormatModule(event.player, firstJoinMessage)
            event.joinMessage = formatModule.replaceAccessFormat(false)
        } else {
            val formatModule = FormatModule(event.player, joinMessage)
            event.joinMessage = formatModule.replaceAccessFormat(false)
        }
    }

    @EventHandler
    fun onQuit(event: PlayerQuitEvent) {
        val formatModule = FormatModule(event.player, quitMessage)
        event.quitMessage = formatModule.replaceAccessFormat(true)
    }
}