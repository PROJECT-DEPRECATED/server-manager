package net.projecttl.server.manager

import net.projecttl.server.manager.listeners.DeadListener
import org.bukkit.plugin.java.JavaPlugin
import net.projecttl.server.manager.listeners.JoinQuitListener
import net.projecttl.server.manager.listeners.MessageListener
import net.projecttl.server.manager.utils.InitSQLDriver

class ManagerPlugin: JavaPlugin() {

    override fun onEnable() {
        if (!dataFolder.exists()) {
            saveDefaultConfig()
        }

        val sql = InitSQLDriver(this)
        sql.loadSQLModule()
        server.pluginManager.apply {
            registerEvents(JoinQuitListener(this@ManagerPlugin), this@ManagerPlugin)
            registerEvents(MessageListener(), this@ManagerPlugin)
            registerEvents(DeadListener(this@ManagerPlugin), this@ManagerPlugin)
        }
    }

    override fun onDisable() {
        val sql = InitSQLDriver(this)
        sql.closeConnection()
        saveConfig()
    }
}