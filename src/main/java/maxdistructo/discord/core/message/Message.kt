package maxdistructo.discord.core.message

import maxdistructo.discord.core.Client
import sx.blah.discord.api.internal.json.objects.EmbedObject
import sx.blah.discord.handle.impl.obj.ReactionEmoji
import sx.blah.discord.handle.obj.*
import sx.blah.discord.util.*
import java.io.File
import java.io.FileNotFoundException
import java.time.Instant
import java.util.*

/**
 * @object Message
 * @description Contains methods to send messages to Discord as well as a Simple Embedded Message builder
 * @author MaxDistructo
 */

object Message {
    var channel: IPrivateChannel? = null
    fun simpleEmbed(user: IUser, title: String, description: String, message: IMessage): EmbedObject {
        val builder = EmbedBuilder()
        val authorAvatar = user.avatarURL
        val guild = message.guild
        //Guild guildJDA = jda.getGuildById(message.getGuild().getLongID());
        //Member member = guildJDA.getMemberById(user.getLongID());
        val color = user.getColorForGuild(message.guild)
        val guildImage = guild.iconURL
        //String guildImage = guild.getIconUrl();
        val guildName = guild.name
        //String guildName = guild.getName();


        //builder.appendField(title1, content1, true);
        //  builder.appendField(title2, content2, true);
        // builder.appendField("fieldTitleNotInline", "fieldContentNotInline", false);
        //  builder.appendField(":tada: fieldWithCoolThings :tada:", "[hiddenLink](http://i.imgur.com/Y9utuDe.png)", false);

        builder.withAuthorName(user.name + "#" + user.discriminator)
        builder.withAuthorIcon(authorAvatar)

        //builder.withDesc("withDesc");
        builder.withDescription(description)
        builder.withTitle(title)
        builder.withTimestamp(Instant.now())
        // builder.withUrl("http://i.imgur.com/IrEVKQq.png");
        // builder.withImage("http://i.imgur.com/agsp5Re.png");

        builder.withFooterIcon(guildImage)
        builder.withFooterText(guildName)
        builder.withColor(color)
        // builder.withFooterIcon("http://i.imgur.com/TELh8OT.png");
        //builder.withThumbnail("http://i.imgur.com/7heQOCt.png");

        //builder.appendDesc(" + appendDesc");
        //  builder.appendDescription(" + appendDescription");

        return builder.build()
    }

    fun sendDM(user: IUser, message: String) : IMessage {
        var pm: IPrivateChannel? = null
        try {
            pm = user.orCreatePMChannel
        } catch (e: RateLimitException) {
            e.printStackTrace()
        } catch (e: DiscordException) {
            e.printStackTrace()
        }
        lateinit var builder : IMessage
        try {
            assert(pm != null)
            builder = pm!!.sendMessage(message)
        } catch (e: MissingPermissionsException) {
            e.printStackTrace()
        } catch (e: DiscordException) {
            e.printStackTrace()
        } catch (e: RateLimitException) {
            e.printStackTrace()
        }
        return builder
    }

    fun sendDM(user: IUser, embed: EmbedObject) : IMessage {
        lateinit var builder : IMessage
        var pm: IPrivateChannel? = null
        try {
            pm = user.orCreatePMChannel
        } catch (e: RateLimitException) {
            e.printStackTrace()
        } catch (e: DiscordException) {
            e.printStackTrace()
        }
        try {
            assert(pm != null)
            builder = pm!!.sendMessage(embed)
        } catch (e: MissingPermissionsException) {
            e.printStackTrace()
        } catch (e: DiscordException) {
            e.printStackTrace()
        } catch (e: RateLimitException) {
            e.printStackTrace()
        }
        return builder
    }
    fun sendMessage(channel: IChannel, content: String) : IMessage {
        lateinit var builder : IMessage
        try {
          builder =  MessageBuilder(Client.client).withChannel(channel).withContent(content).build()
        } catch (e: RateLimitException) {
            e.printStackTrace()
        } catch (e: DiscordException) {
            e.printStackTrace()
        } catch (e: MissingPermissionsException) {
            e.printStackTrace()
        }
        return builder
    }

    fun sendMessage(channel: IChannel, embedded: EmbedObject) : IMessage{
        return MessageBuilder(Client.client).withChannel(channel).withEmbed(embedded).build()
    }

    fun sendMessage(channel: IChannel, file: File) : IMessage? {
        try {
            return MessageBuilder(Client.client).withChannel(channel).withFile(file).build()
        } catch (e: FileNotFoundException) {
            e.printStackTrace()
        }
        return null
    }

    @Deprecated("Old Method.", ReplaceWith("throwError(e : Exception)", "")) //Use #Message.throwError(Exception e, IMessage message); or #Message.throwError(Exception e);
    fun sendError(e: Exception) {
        sendDM(Client.client!!.applicationOwner, e.toString() + "\n" + Arrays.toString(e.stackTrace)) //General Support
    }

    fun throwError(e: Exception) {
        sendDM(Client.client!!.applicationOwner, e.toString() + "\n" + Arrays.toString(e.stackTrace)) //General Support
    }

    fun throwError(e: Exception, message: IMessage) {
        sendDM(Client.client!!.applicationOwner, message.guild.name + "'s #" + message.channel.name + " has thrown " + e.toString() + "\n" + Arrays.toString(e.stackTrace)) //General Support
    }
}

