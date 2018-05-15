package maxdistructo.discord.core

import org.slf4j.Logger
import sx.blah.discord.api.IDiscordClient

/**
*
* This class is used for one purpose and that is to hold variables necessary for the rest of the core to work. This includes the client and LOGGER variables.
*
*/

object Client {
    var client: IDiscordClient? = null
    var LOGGER : Logger? = null
}
