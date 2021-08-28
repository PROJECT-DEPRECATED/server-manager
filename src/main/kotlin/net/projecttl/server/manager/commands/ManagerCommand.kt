package net.projecttl.server.manager.commands

import net.projecttl.server.manager.utils.PlayerList
import org.bukkit.command.Command
import org.bukkit.command.CommandExecutor
import org.bukkit.command.CommandSender
import org.bukkit.command.TabCompleter
import org.bukkit.entity.Player
import org.bukkit.plugin.java.JavaPlugin

class ManagerCommand(private val plugin: JavaPlugin) : CommandExecutor, TabCompleter {

    override fun onCommand(sender: CommandSender, command: Command, label: String, args: Array<out String>): Boolean {
        if (command.name == "manager") {
            if (sender.isOp) {
                return when (args.size) {
                    0 -> {
                        if (sender is Player) {
                            TODO("Open gui")
                        }

                        true
                    }

                    1 -> {
                        return when (args[0]) {
                            "online" -> {
                                val list = PlayerList()
                                sender.sendMessage(list.queryPlayer())

                                true
                            }

                            else -> false
                        }
                    }

                    else -> false
                }
            }
        }

        return false
    }

    override fun onTabComplete(sender: CommandSender, command: Command, alias: String, args: Array<out String>): MutableList<String>? {
        if (command.name == "manager") {
            val commandList = mutableListOf<String>()
            return when (args.size) {
                1 -> {
                    commandList.add("online")
                    commandList
                }

                else -> {
                    null
                }
            }
        }
        return null
    }
}