package net.projecttl.server.manager.utils

import org.bukkit.Bukkit
import org.bukkit.ChatColor
import org.bukkit.entity.Player
import org.bukkit.event.entity.PlayerDeathEvent
import org.bukkit.event.player.PlayerEvent

class FormatModule(private val player: Player, private var formatter: String) {

    private val listColor: List<String> = listOf(
        "<black>",
        "<blue>",
        "<dark_green>",
        "<dark_aqua>",
        "<dark_red>",
        "<dark_purple>",
        "<gold>",
        "<gray>",
        "<dark_gray>",
        "<blue>",
        "<green>",
        "<aqua>",
        "<red>",
        "<light_purple>",
        "<yellow>",
        "<white>",
        "<magic>",
        "<bold>",
        "<strike_through>",
        "<under_line>",
        "<italic>",
        "<reset>"
    )

    companion object {
        fun replaceChatColor(message: String): String {
            return message.replace("<black>", "${ChatColor.BLACK}")
                .replace("<blue>", "${ChatColor.DARK_BLUE}")
                .replace("<dark_green>", "${ChatColor.DARK_GREEN}")
                .replace("<dark_aqua>", "${ChatColor.DARK_AQUA}")
                .replace("<dark_red>", "${ChatColor.DARK_RED}")
                .replace("<dark_purple>", "${ChatColor.DARK_PURPLE}")
                .replace("<gold>", "${ChatColor.GOLD}")
                .replace("<gray>", "${ChatColor.GRAY}")
                .replace("<dark_gray>", "${ChatColor.DARK_GRAY}")
                .replace("<blue>", "${ChatColor.BLUE}")
                .replace("<green>", "${ChatColor.GREEN}")
                .replace("<aqua>", "${ChatColor.AQUA}")
                .replace("<red>", "${ChatColor.RED}")
                .replace("<light_purple>", "${ChatColor.LIGHT_PURPLE}")
                .replace("<yellow>", "${ChatColor.YELLOW}")
                .replace("<white>", "${ChatColor.WHITE}")
                .replace("<magic>", "${ChatColor.MAGIC}")
                .replace("<bold>", "${ChatColor.BOLD}")
                .replace("<strike_through>", "${ChatColor.STRIKETHROUGH}")
                .replace("<under_line>", "${ChatColor.UNDERLINE}")
                .replace("<italic>", "${ChatColor.ITALIC}")
                .replace("<reset>", "${ChatColor.RESET}")
        }
    }

    fun replacePlayer(): String {
        return replaceChatColor(formatter.replace("<player>", player.name)
            .replace("<address>", player.address?.address!!.hostAddress)
            .replace("<exp>", "${player.exp}")
            .replace("<level>", "${player.level}"))
    }

    fun replaceAccessFormat(leave: Boolean): String {
        val player: String = replacePlayer()

        return if (!leave) {
            replaceChatColor(player.replace("<online>", "${Bukkit.getOnlinePlayers().size}")
                .replace("<max>", "${Bukkit.getMaxPlayers()}"))
        } else {
            replaceChatColor(player.replace("<online>", "${Bukkit.getOnlinePlayers().size - 1}")
                .replace("<max>", "${Bukkit.getMaxPlayers()}"))
        }
    }
}