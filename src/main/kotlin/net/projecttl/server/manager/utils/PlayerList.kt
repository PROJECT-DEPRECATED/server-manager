package net.projecttl.server.manager.utils

import org.bukkit.Bukkit
import org.bukkit.ChatColor
import org.bukkit.entity.Player
import java.sql.Statement

class PlayerList {

    private val statement: Statement = InitSQLDriver.sqlConnection.createStatement()

    fun addPlayer(player: Player) {
        statement.executeUpdate("insert into player values ('${player.uniqueId}', '${player.name}');")
    }

    fun queryPlayer(): String {
        var line = ""
        val resultSets = statement.executeQuery("select username from player;")
        while (resultSets.next()) {
            line += "${resultSets.getString("username")}, "

        }

        return line
    }
}