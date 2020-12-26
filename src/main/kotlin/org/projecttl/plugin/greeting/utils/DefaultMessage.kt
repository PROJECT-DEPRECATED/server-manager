package org.projecttl.plugin.greeting.utils

import org.projecttl.plugin.greeting.GreetingPlugin

class DefaultMessage(instance: GreetingPlugin) {
    private var plugin: GreetingPlugin? = null

    init {
        plugin = instance
    }

    fun default() {
        plugin?.getGreetingConfig()?.set("Greeting.JoinMessage", ">§a has Join this Server!")
        plugin?.getGreetingConfig()?.set("Greeting.QuitMessage", ">§c has Left this Server!")
    }
}