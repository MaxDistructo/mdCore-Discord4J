package maxdistructo.droidbot2.core

import sx.blah.discord.handle.obj.*
import org.json.*
import java.lang.StringBuilder

object Help2 {

    fun helpCommand(message: IMessage): String {
        val guild = message.guild
        val user = message.author
        val config = Config.readServerConfig(guild)
        val commandsObject = config.getJSONObject("Commands")
        val commandsJSON = commandsObject.getJSONArray("commands")
        val commands = Utils.toStringArray(commandsJSON)
        val helpCommandBuilder = StringBuilder()
        var i = 0
        while (i < commands!!.size) {
            val command = commandsObject.getJSONObject(commands[i])

            if (command.getBoolean("moderator") && Perms.checkMod(message)) {
                helpCommandBuilder.append(commands[i] + ": " + command.getString("disc") + "\n")
            } else if (!command.getBoolean("moderator") && !Perms.checkMod(message)) {
                helpCommandBuilder.append(commands[i] + ": " + command.getString("disc") + "\n")
            }
            i++
        }
        return helpCommandBuilder.toString()
    }

}
