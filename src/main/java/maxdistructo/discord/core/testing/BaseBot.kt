package maxdistructo.discord.core.testing

import maxdistructo.discord.core.Config
import maxdistructo.discord.core.impl.Bot
import org.slf4j.Logger
import sx.blah.discord.api.IDiscordClient

object BaseBot {
    lateinit var client: IDiscordClient
    lateinit var LOGGER : Logger
    lateinit var bot : Bot

    @JvmStatic
    fun main(args: Array<String>) {
        bot = Bot(Config.readToken())
        client = bot.client
        LOGGER = bot.logger
        LOGGER.info("Testing Client Created")
        client.dispatcher.registerListener(Listener())
        LOGGER.info("Listener Registered")
    }

}