package net.projecttl.server.manager.utils

import org.bukkit.plugin.java.JavaPlugin

class StyleTag(plugin: JavaPlugin) {

    private var tagList: MutableList<String> = plugin.config.getStringList("style-tag")

    var tag: String
    get() {
        var list = ""
        for ((i, j) in tagList.withIndex()) {
            list += "#$i tag: ${j[i]}\n"
        }

        return list
    }
    set(tag_name) {
        val list = mutableListOf<String>()
        list.add(FormatModule.replaceChatColor("$tag_name<reset>"))

        tagList = list
    }
}