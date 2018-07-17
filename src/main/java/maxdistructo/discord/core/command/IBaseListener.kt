package maxdistructo.discord.core.command

import sx.blah.discord.api.events.EventSubscriber
import sx.blah.discord.handle.impl.events.guild.channel.message.MessageReceivedEvent
import java.util.*

interface IBaseListener {
    fun registerCommand(vararg commands : BaseCommand){
        for(command in commands) {
            when {
                command.requiresAdmin -> {
                    adminCommands += command
                }
                command.requiresMod -> {
                    adminCommands += command
                    modCommands += command
                }
                else -> {
                    adminCommands += command
                    modCommands += command
                    commandsArray += command
                }
            }
        }
    }

    @EventSubscriber
    fun onMessageReceivedEvent(event : MessageReceivedEvent)
    var commandsArray : List<BaseCommand>
    var adminCommands : List<BaseCommand>
    var modCommands : List<BaseCommand>
    val name : String

    var commandRegistry : LinkedList<ICommand>
    fun addCommand(command : ICommand)
    fun createCommands()

}