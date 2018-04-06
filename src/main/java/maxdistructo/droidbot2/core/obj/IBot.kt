package maxdistructo.droidbot2.core.obj

import maxdistructo.droidbot2.core.impl.Help
import org.slf4j.Logger
import sx.blah.discord.api.IDiscordClient

interface IBot {

    val token : String
    val client : IDiscordClient
    var help : Help
    val logger : Logger

}