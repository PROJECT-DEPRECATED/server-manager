package net.projecttl.server.manager.utils

import org.bukkit.plugin.Plugin
import java.io.File
import java.sql.Connection
import java.sql.DriverManager
import java.sql.SQLException
import java.sql.Statement

class InitSQLDriver(private val plugin: Plugin) {
    companion object {
        lateinit var sqlConnection: Connection
    }

    private val logger = plugin.logger

    init {
        val file = File(plugin.dataFolder, "database.db")
        if (!file.exists()) {
            file.createNewFile()
        }
    }

    fun loadSQLModule() {
        logger.info("Loading driver...")

        Class.forName("org.sqlite.JDBC")
        plugin.logger.info("Connecting to SQL...")

        val loggerInfo = "${plugin.dataFolder}/database.db"

        try {
            sqlConnection = DriverManager.getConnection("jdbc:sqlite:$loggerInfo/")
            logger.info("Connected!")
        } catch (exception: SQLException) {
            exception.printStackTrace()
        } catch (exception: Exception) {
            exception.printStackTrace()
        }

        val statement: Statement = sqlConnection.createStatement()
        statement.executeUpdate("create table if not exists player(" +
                "`uuid` varchar(36) not null," +
                "`username` varchar(25)  not null," +
                "primary key (uuid)" +
                ");")
    }

    fun openConnection() {
        logger.info("Loading driver...")

        Class.forName("org.sqlite.JDBC")
        plugin.logger.info("Connecting to SQL...")

        val loggerInfo = "${plugin.dataFolder}/database.db"

        try {
            sqlConnection = DriverManager.getConnection("jdbc:sqlite:$loggerInfo/")
            logger.info("Connected!")
        } catch (exception: SQLException) {
            exception.printStackTrace()
        } catch (exception: Exception) {
            exception.printStackTrace()
        }
    }

    fun closeConnection() {
        try {
            if (!sqlConnection.isClosed) {
                sqlConnection.close()
            }
        } catch (e: SQLException) {
            e.printStackTrace()
        }
    }
}