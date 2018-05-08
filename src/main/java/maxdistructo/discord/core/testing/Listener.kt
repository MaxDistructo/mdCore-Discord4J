package maxdistructo.discord.core.testing

import maxdistructo.discord.core.testing.BaseBot.bot
import maxdistructo.discord.core.message.Webhook
import sx.blah.discord.handle.impl.events.guild.channel.message.MessageReceivedEvent

class Listener{

@EventSubscriber
fun onMessageRecievedEvent(event : MessageReceivedEvent){

  val logger = bot.logger
  val message = event.message
  val guild = event.guild
  val channel = event.channel
  val user = message.author
  val contents = event.message.content.split(" ")
  
    if(user == bot.client.applicationOwner){
      logger.info("Accepted Message: " + message.longID + " from " + user.name)
      logger.warn("Attempting to send webhook back in channel " + channel.name)
      Webhook.send(bot, channel, message.content)
      logger.warn("Sent message to Discord for the webhook")
    }
}
}
