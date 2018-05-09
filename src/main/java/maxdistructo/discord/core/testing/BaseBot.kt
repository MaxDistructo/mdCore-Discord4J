package maxdistructo.discord.core.testing

import maxdistructo.discord.core.Config
import maxdistructo.discord.core.impl.Bot
import org.slf4j.Logger
import sx.blah.discord.api.IDiscordClient

lateinit var bot : Bot
lateinit var client: IDiscordClient
lateinit var LOGGER : Logger

fun main(args : Array<String>){
  bot = Bot(Config.readToken())
  client = bot.client
  LOGGER = bot.logger
  LOGGER.info("Testing Bot loaded")
  client.dispatcher.registerListener(Listener())
  LOGGER.info("Registered Testing Listener")
  
}
