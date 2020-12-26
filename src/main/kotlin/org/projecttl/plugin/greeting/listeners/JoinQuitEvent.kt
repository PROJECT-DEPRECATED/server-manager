package org.projecttl.plugin.greeting.listeners

import org.bukkit.entity.Player
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.player.PlayerJoinEvent
import org.bukkit.event.player.PlayerQuitEvent
import org.projecttl.plugin.greeting.GreetingPlugin

class JoinQuitEvent(instance: GreetingPlugin): Listener {

    private var plugin: GreetingPlugin? = null
    init {
        plugin = instance
    }

    private var playerNameColor = plugin!!.getGreetingConfig()?.getString("Greeting.PlayerNameColor")
    private var joinMessage = plugin!!.getGreetingConfig()?.getString("Greeting.JoinMessage")
    private var quitMessage = plugin!!.getGreetingConfig()?.getString("Greeting.QuitMessage")

    @EventHandler
    fun onJoin(event: PlayerJoinEvent) {
        val player = event.player as Player
        event.joinMessage = "${playerNameColor}${player.name}${joinMessage}"
    }

    @EventHandler
    fun onQuit(event: PlayerQuitEvent) {
        val player = event.player as Player
        event.quitMessage = "${playerNameColor}${player.name}${quitMessage}"
    }
}