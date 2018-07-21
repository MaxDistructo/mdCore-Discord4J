package maxdistructo.discord.core.command

import sx.blah.discord.handle.obj.IMessage

/**
 * @interface ICommand
 * @description The base of all commands supported by my system. You may implement your own version of this as needed.
 * @author MaxDistructo
 */

interface ICommand{
    fun init(message : IMessage, args : List<String>) : String{
        return "Command Error: $commandName"
    }
    val requiresMod : Boolean
    val requiresAdmin : Boolean
    val requiresGuildOwner : Boolean
    val requiresOwner : Boolean
    val commandName : String
    val helpMessage : String
    val hasOutput : Boolean
    val commandType : Enum<ICommandType>
}