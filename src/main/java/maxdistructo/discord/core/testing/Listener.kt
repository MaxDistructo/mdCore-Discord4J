package maxdistructo.discord.core.testing

import maxdistructo.discord.core.message.Webhook
import sx.blah.discord.api.events.EventSubscriber
import sx.blah.discord.handle.impl.events.guild.channel.message.MessageReceivedEvent
import sx.blah.discord.util.DiscordException
import sx.blah.discord.util.MissingPermissionsException
import sx.blah.discord.util.RateLimitException

class Listener {

    @EventSubscriber
    @Throws(RateLimitException::class, DiscordException::class, MissingPermissionsException::class)
    fun onMessageReceivedEvent(event: MessageReceivedEvent) {
            if(!event.message.author.isBot && event.message.author == BaseBot.bot.client.applicationOwner){
                Webhook.send(BaseBot.bot, event.message.channel, event.message.author.name, event.message.author.avatarURL, event.message.content)
            }

        }
    }
