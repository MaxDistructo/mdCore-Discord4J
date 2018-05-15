package maxdistructo.discord.core.priv

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
