package maxdistructo.discord.core.testing

lateint var bot : Bot
lateinit var client: IDiscordClient
lateinit var LOGGER : Logger

@JvmStatic
fun main(args : Array<String>()){
  bot = Bot(Config.readToken())
  client = bot.client
  LOGGER = bot.logger
  LOGGER.info("Testing Bot loaded")
  client.dispatcher.registerListener(Listener())
  LOGGER.info("Registered Testing Listener")
  
}
