package maxdistructo.discord.core.obj

import maxdistructo.discord.core.impl.Help
import org.slf4j.Logger
import sx.blah.discord.api.IDiscordClient

interface IBot {

    val token : String
    val client : IDiscordClient
    var help : Help
    val logger : Logger

}