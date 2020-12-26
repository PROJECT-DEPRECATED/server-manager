package org.projecttl.plugin.greeting.commands

import org.bukkit.Bukkit
import org.bukkit.ChatColor
import org.bukkit.command.Command
import org.bukkit.command.CommandExecutor
import org.bukkit.command.CommandSender
import org.projecttl.plugin.greeting.utils.DefaultMessage
import org.projecttl.plugin.greeting.GreetingPlugin

class GreetingCommand(instance: GreetingPlugin): CommandExecutor {

    private var plugin: GreetingPlugin? = null
    init {
        plugin = instance
    }

    override fun onCommand(sender: CommandSender, command: Command, label: String, args: Array<out String>): Boolean {
        if (command.name.equals("greeting", ignoreCase = true) && sender.hasPermission("plugin.greeting.op")) {
            val default = DefaultMessage(plugin!!)

            if (args.isNullOrEmpty()) {
                return false
            }

            else if (args.size == 1) {
                if (args[0].equals("default", ignoreCase = true)) {
                    default.default()
                    Bukkit.broadcastMessage("<Greeting_Plugin> ${ChatColor.GREEN}Join Quit Message has successful reset!")
                    return true
                }
            }
        }

        return false
    }
}