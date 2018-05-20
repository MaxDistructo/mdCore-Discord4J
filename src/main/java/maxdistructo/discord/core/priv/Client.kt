package maxdistructo.discord.core.priv

import sx.blah.discord.api.ClientBuilder
import sx.blah.discord.api.IDiscordClient
import sx.blah.discord.util.DiscordException

object Client{
  fun createClient(token : String) : IDiscordClient?{
        lateinit var client : IDiscordClient
        val clientBuilder = ClientBuilder() // Creates the ClientBuilder instance
        clientBuilder.withToken(token) // A to the builder
        clientBuilder.withRecommendedShardCount()
        try {
            client = clientBuilder.login()
        } catch (e: DiscordException) {
            e.printStackTrace()
            System.exit(0)
        }
        maxdistructo.discord.core.Client.client = client // This variable is used in Message and I can not touch it.
        return client
  }

}
