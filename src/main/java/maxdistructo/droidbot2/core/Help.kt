package maxdistructo.droidbot2.core


import maxdistructo.droidbot2.core.message.Message
import sx.blah.discord.handle.obj.*

import java.util.Arrays

//All DroidBot2-Core Modules will implement into this help code. If you would like to add your own Help code to it, please create a method that adds your Commands into the help array that is read for this command.
object Help {

    fun onHelpCommand(message: IMessage) {

        val helpArray = Config.readHelp() //Method will return just the array from the Config file.
        if (Perms.checkAdmin(message)) {
            val modHelpArray = Config.readModHelp() //In order to allow for mods to have seperate help for mod only commands, this must be done.
            val adminHelpArray = Config.readAdminHelp()
            val help1 = Utils.makeNewLineString(helpArray, 1)
            val help2 = Utils.makeNewLineString(modHelpArray, 1)
            val help3 = Utils.makeNewLineString(adminHelpArray, 1)
            Message.sendDM(message.author, "$help1\n Moderator Commands: \n$help2\n Admin Commands: \n$help3")
        } else if (Perms.checkMod(message)) {
            val modHelpArray = Config.readModHelp() //In order to allow for mods to have seperate help for mod only commands, this must be done.
            val help1 = Utils.makeNewLineString(helpArray, 1)
            val help2 = Utils.makeNewLineString(modHelpArray, 1)
            Message.sendDM(message.author, "$help1\n Moderator Commands: \n$help2")
        } else {
            val help = Utils.makeNewLineString(helpArray, 1)
            Message.sendDM(message.author, help)
        }
    }

    fun addCommand(`in`: String) { //Call this method to add your commands to the array.

        val helpArray = Config.readHelp()
        val help = Arrays.asList(*helpArray)
        help.add(`in`)
        Config.writeHelp(help as List<String>)

    }

    fun addModCommand(`in`: String) {

        val modHelpArray = Config.readModHelp()
        val help = Arrays.asList(*modHelpArray)
        help.add(`in`)
        Config.writeHelp(help as List<String>)

    }

    fun addAdminCommand(`in`: String) { //Call this method to add your commands to the array.

        val helpArray = Config.readHelp()
        val help = Arrays.asList(*helpArray)
        help.add(`in`)
        Config.writeHelp(help as List<String>)

    }

}
