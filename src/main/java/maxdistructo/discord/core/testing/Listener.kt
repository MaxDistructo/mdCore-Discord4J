package maxdistructo.discord.core.testing

import maxdistructo.discord.core.testing.BaseBot.bot

class Listener{

fun onMessageRecievedEvent(event : MessageRecievedEvent){

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
