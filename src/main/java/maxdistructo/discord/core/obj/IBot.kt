package maxdistructo.discord.core.obj

import maxdistructo.discord.core.command.IBaseListener
import org.slf4j.Logger
import sx.blah.discord.api.IDiscordClient
import java.util.*

/**
 * @interface IBot
 * @description Simple Discord Bot that implements my Listener and Command systems. May be implemented using your own features.
 * @author MaxDistructo
 */

interface IBot {

    val token : String
    val client : IDiscordClient
    val logger : Logger
    val listeners : LinkedList<IBaseListener>
    fun registerListeners()
    fun addListener(listener: IBaseListener)

}