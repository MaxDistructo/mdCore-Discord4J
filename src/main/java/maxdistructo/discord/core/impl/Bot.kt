package maxdistructo.discord.core.impl

import maxdistructo.discord.core.obj.IBot
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import sx.blah.discord.api.ClientBuilder
import sx.blah.discord.api.IDiscordClient

class Bot (token : String): IBot {
    private val privToken : String = token
    private var privHelp = Help()
    private val privClient = ClientBuilder().withToken(token).withRecommendedShardCount().login()

    override val token: String
        get() = privToken
    override val client: IDiscordClient
        get() = privClient
    override var help: Help
        get() = privHelp
        set(input){
            privHelp = input
        }
    override val logger: Logger
        get() = LoggerFactory.getLogger(client.applicationName)
}