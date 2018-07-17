package maxdistructo.discord.core.impl

import maxdistructo.discord.core.command.IBaseListener
import maxdistructo.discord.core.obj.IBot
import maxdistructo.discord.core.priv.Client
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import sx.blah.discord.api.IDiscordClient
import java.util.*

class Bot (token : String): IBot {

    override val listeners = LinkedList<IBaseListener>()
    private val privToken : String = token
    private val privClient = Client.createClient(token)!!

    override val token: String
        get() = privToken
    override val client: IDiscordClient
        get() = privClient
    override val logger: Logger
        get() = LoggerFactory.getLogger(client.applicationName)

    override fun registerListeners() {
        for(listener in listeners){
            listener.createCommands() //Must add commands to listener which grabs commands from ICommandRegistry and registers them to itself.
            privClient.dispatcher.registerListener(listener)
            logger.info("Registered Listener: " + listener.name)
        }
    }

    override fun addListener(listener: IBaseListener) {
        listeners += listener
    }
}
