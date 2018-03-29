package maxdistructo.droidbot2.core.obj

import maxdistructo.droidbot2.core.obj.impl.Help
import org.slf4j.Logger
import sx.blah.discord.api.IDiscordClient

object IBot{
  
  val help : Help
  val prefix : String
  val client : IDiscordClient
  val logger : Logger
 
}
