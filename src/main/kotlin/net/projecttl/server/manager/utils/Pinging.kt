package net.projecttl.server.manager.utils

import org.bukkit.Sound
import org.bukkit.entity.Player

class Pinging(player: Player) {
    init {
        player.playSound(player.location, Sound.BLOCK_BELL_USE, 100F, 2F)
    }
}