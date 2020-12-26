package org.projecttl.plugin.greeting

import org.bukkit.Bukkit
import org.bukkit.configuration.file.FileConfiguration
import org.bukkit.configuration.file.YamlConfiguration
import org.bukkit.plugin.java.JavaPlugin
import org.projecttl.plugin.greeting.commands.GreetingCommand
import org.projecttl.plugin.greeting.commands.arguments.ArgumentForGreetingCommand
import org.projecttl.plugin.greeting.listeners.JoinQuitEvent
import org.projecttl.plugin.greeting.utils.DefaultMessage
import java.io.File

class GreetingPlugin: JavaPlugin() {

    private var getFile: File? = null
    private var configuration: FileConfiguration? = null
    private var manager = server.pluginManager

    override fun onEnable() {
        load()
        logger.info("Plugin has enabled.")

        // Command and Tab Completer
        getCommand("greeting")?.executor = GreetingCommand(this)
        getCommand("greeting")?.tabCompleter = ArgumentForGreetingCommand()

        manager.registerEvents(JoinQuitEvent(this), this)
    }

    override fun onDisable() {
        save()
        logger.info("Plugin has disabled.")
    }

    private fun load() {
        val defaultMessage = DefaultMessage(this)
        getFile = File(dataFolder, "greeting.yml")
        configuration = YamlConfiguration.loadConfiguration(getFile)

        try {
            if (!getFile!!.exists()) {
                defaultMessage.default()
                configuration?.save(getFile!!)
            }

            configuration?.load(getFile!!)
        } catch (exception: Exception) {
            exception.printStackTrace()
        }
    }

    private fun save() {
        configuration?.save(getFile)
        Bukkit.broadcastMessage("<Greeting_Plugin> The config has successful saved.")
    }

    fun getGreetingConfig(): FileConfiguration? {
        return this.configuration
    }
}