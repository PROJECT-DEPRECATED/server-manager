package net.projecttl.server.manager.listeners

import net.projecttl.server.manager.utils.Pinging
import org.bukkit.Bukkit
import org.bukkit.ChatColor
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.player.AsyncPlayerChatEvent

class MessageListener : Listener {

    @EventHandler
    fun onMessage(event: AsyncPlayerChatEvent) {
        for (i in Bukkit.getOnlinePlayers()) {
            println(i.name)
            if (event.message.contains("@${i.name}")) {
                event.message.replace("@${i.name}", "${ChatColor.YELLOW}@${i.name}")
                Pinging(i)
            }
        }
    }
}