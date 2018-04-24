package maxdistructo.droidbot2.core

import org.slf4j.Logger
import org.slf4j.LoggerFactory
import sx.blah.discord.api.ClientBuilder
import sx.blah.discord.api.IDiscordClient
import sx.blah.discord.util.DiscordException

@Deprecated("Use a Bot object instead!")
object Client {
    var client: IDiscordClient? = null
    var prefix: String = "!"
    val LOGGER = LoggerFactory.getLogger(Client::class.java!!)
    fun createClient(token: String): IDiscordClient? { // Returns a new instance of the Discord client
        val clientBuilder = ClientBuilder() // Creates the ClientBuilder instance
        clientBuilder.withToken(token) // A to the builder
        clientBuilder.withRecommendedShardCount()
        try {
            client = clientBuilder.login()
        } catch (e: DiscordException) {
            e.printStackTrace()
            System.exit(0)
        }

        prefix = Config.readPrefix()
        return client
    }

    fun registerListener(listener: Any) {
        client!!.dispatcher.registerListener(listener)
    }
}
