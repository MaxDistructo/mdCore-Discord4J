package maxdistructo.droidbot2.core.message

import maxdistructo.droidbot2.core.Client
import sx.blah.discord.api.internal.json.objects.EmbedObject
import sx.blah.discord.handle.impl.obj.ReactionEmoji
import sx.blah.discord.handle.obj.*
import sx.blah.discord.util.*

import java.awt.*
import java.io.File
import java.io.FileNotFoundException
import java.time.Instant
import java.time.LocalDateTime
import java.util.Arrays


class Message {
    var channel: IPrivateChannel? = null

    companion object {

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

        fun sendDM(user: IUser, message: String) {
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
                pm!!.sendMessage(message)
            } catch (e: MissingPermissionsException) {
                e.printStackTrace()
            } catch (e: DiscordException) {
                e.printStackTrace()
            } catch (e: RateLimitException) {
                e.printStackTrace()
            }
        }
        fun sendDM(user: IUser, embed : EmbedObject) {
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
                pm!!.sendMessage(embed)
            } catch (e: MissingPermissionsException) {
                e.printStackTrace()
            } catch (e: DiscordException) {
                e.printStackTrace()
            } catch (e: RateLimitException) {
                e.printStackTrace()
            }
        }

        fun react(message: IMessage, emote: IEmoji) {
            message.addReaction(ReactionEmoji.of(emote))
        }

        fun react(message: IMessage, emote: String) {
            message.addReaction(ReactionEmoji.of(emote))
        }

        fun react(message: IMessage, guild: IGuild, emote: String) {
            message.addReaction(ReactionEmoji.of(emote, guild.longID))
        }

        fun sendMessage(channel: IChannel, content: String) {
            try {
                MessageBuilder(Client.client).withChannel(channel).withContent(content).build()
            } catch (e: RateLimitException) {
                e.printStackTrace()
            } catch (e: DiscordException) {
                e.printStackTrace()
            } catch (e: MissingPermissionsException) {
                e.printStackTrace()
            }

        }

        fun sendMessage(channel: IChannel, embedded: EmbedObject) {
            MessageBuilder(Client.client).withChannel(channel).withEmbed(embedded).build()
        }

        fun sendMessage(channel: IChannel, file: File) {
            try {
                MessageBuilder(Client.client).withChannel(channel).withFile(file).build()
            } catch (e: FileNotFoundException) {
                e.printStackTrace()
            }

        }

        @Deprecated("Old Method.") //Use #Message.throwError(Exception e, IMessage message); or #Message.throwError(Exception e);
        fun sendError(e: Exception) {
            sendDM(Client.client!!.applicationOwner, e.toString() + "\n" + Arrays.toString(e.stackTrace)) //General Support
            sendDM(Client.client!!.getUserByID(374517920505790464L), e.toString() + "\n" + Arrays.toString(e.stackTrace)) //Secondary Account Support
        }

        fun throwError(e: Exception) {
            sendDM(Client.client!!.applicationOwner, e.toString() + "\n" + Arrays.toString(e.stackTrace)) //General Support
            sendDM(Client.client!!.getUserByID(374517920505790464L), e.toString() + "\n" + Arrays.toString(e.stackTrace)) //Secondary Account Support
        }

        fun throwError(e: Exception, message: IMessage) {
            sendDM(Client.client!!.applicationOwner, message.guild.name + "'s #" + message.channel.name + " has thrown " + e.toString() + "\n" + Arrays.toString(e.stackTrace)) //General Support
            sendDM(Client.client!!.getUserByID(374517920505790464L), message.guild.name + "'s #" + message.channel.name + " has thrown " + e.toString() + "\n" + Arrays.toString(e.stackTrace)) //Secondary Account Support
        }
    }

}
