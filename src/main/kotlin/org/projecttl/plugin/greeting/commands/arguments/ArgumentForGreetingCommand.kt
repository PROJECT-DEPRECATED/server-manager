package org.projecttl.plugin.greeting.commands.arguments

import org.bukkit.command.Command
import org.bukkit.command.CommandSender
import org.bukkit.command.TabCompleter

class ArgumentForGreetingCommand: TabCompleter {

    override fun onTabComplete(sender: CommandSender?, command: Command?, alias: String?, args: Array<out String>?): MutableList<String>? {
        if (command?.name.equals("greeting", ignoreCase = true)) {
            if (args?.size == 1) {
                val firstArgument = ArrayList<String>()
                firstArgument.add("default")

                return firstArgument
            }
        }

        return null
    }
}