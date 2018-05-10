package maxdistructo.discord.core.message

/*
This code borrows some of the IWebhook handling code from sx.blah.discord.handle.impl.obj.Guild.java
The above mentioned code was released under a GNU Lesser General Public License at https://github.com/Discord4J/Discord4J
The only modification of this code was a convert to Kotlin.

WARNING: This is UNTESTED and HIGHLY EXPERIMENTAL CODE!
*/

import com.mashape.unirest.http.Unirest
import maxdistructo.discord.core.Utils
import maxdistructo.discord.core.Utils.s
import maxdistructo.discord.core.impl.Bot
import sx.blah.discord.api.`internal`.DiscordClientImpl
import sx.blah.discord.api.`internal`.DiscordEndpoints
import sx.blah.discord.api.`internal`.json.objects.WebhookObject
import sx.blah.discord.handle.impl.obj.Webhook
import org.json.JSONObject
import sx.blah.discord.handle.obj.*
import sx.blah.discord.util.RequestBuffer
import java.io.File

object Webhook{

  fun createWebhook(channel : IChannel, name: String, avatar : String) : IWebhook{
    val webhook = channel.createWebhook(name, avatar)
      return webhook
  }
  fun name(channel : IChannel, name : String, newName : String){
    val webhook = channel.getWebhooksByName(name) [0]
    webhook.changeDefaultName(newName)
  }
  fun avatar(channel : IChannel, name : String, avatar : String){
    val webhook = channel.getWebhooksByName(name) [0]
    webhook.changeDefaultAvatar(avatar)
  }
  private fun jsonBuilder(bot: Bot, oldWebhook : Webhook, message : String, name : String, avatar : String) : JSONObject{
      avatar(oldWebhook.channel, oldWebhook.defaultName, avatar)
      name(oldWebhook.channel, oldWebhook.defaultName, name)
      val webhook = getByName(bot, oldWebhook.channel, name)!!
      val out = JSONObject()
        out.put("name", webhook.defaultName)
        out.put("channel_id", webhook.channel.longID)
        out.put("token", webhook.token)
        out.put("avatar", webhook.defaultAvatar)
        out.put("guild_id", webhook.guild.longID)
        out.put("id", webhook.longID)
        out.put("user", JSONObject().put("username", webhook.author.name).put("discriminator", webhook.author.discriminator).put("id", webhook.author.longID).put("avatar", webhook.author.avatar))
        return out
  }
  private fun jsonBuilder(webhook: Webhook, message : String) : JSONObject{
        val out = JSONObject()
        out.put("name", webhook.defaultName)
        out.put("channel_id", webhook.channel.longID)
        out.put("token", webhook.token)
        out.put("avatar", webhook.defaultAvatar)
        out.put("guild_id", webhook.guild.longID)
        out.put("id", webhook.longID)
        out.put("user", JSONObject().put("username", webhook.author.name).put("discriminator", webhook.author.discriminator).put("id", webhook.author.longID).put("avatar", webhook.author.avatar))
         return out
  }
    private fun webhookFromJSON(bot : Bot, json : JSONObject) : Webhook{
        return Webhook(bot.client, json.getString("name"), json.getLong("id"), bot.client.getChannelByID(json.getLong("channel_id")), bot.client.getUserByID(json.getJSONObject("user").getLong("id")),json.getString("avatar"), json.getString("token") )
    }

  fun send(bot : Bot, channel : IChannel, name : String, avatar : String, message : String){
   val webhook = defaultWebhook(bot, channel)
   avatar(channel, "bot", avatar)
   name(channel, "bot", name)
   Unirest.post(DiscordEndpoints.WEBHOOKS + webhook.longID + "/" + webhook.token).body(jsonBuilder(bot, webhook, message, name, avatar))
   name(channel, name, "bot")
   avatar(channel, "bot", "https://www.shareicon.net/download/128x128//2017/06/21/887435_logo_512x512.png")
  }
  fun send(bot : Bot, channel : IChannel, message : String){
   val webhook = defaultWebhook(bot, channel)
   Unirest.post("https://discordapp.com/api/webhooks/" + webhook.longID + "/" + webhook.token)
    .header("Content-Type", "application/json")
    .queryString("content", jsonBuilder(webhook, message).toString())
    .asJson()
  }
  fun send(webhook : Webhook, message: String){
    //TODO Write this method
  }
  fun getByName(bot : Bot, channel : IChannel, name : String) : Webhook?{
    var webhookList = listOf<Webhook>()
    val client = bot.client as DiscordClientImpl
    val webhooks = RequestBuffer.request {
        val webhookObjects = client.REQUESTS.GET.makeRequest(
                DiscordEndpoints.CHANNELS + channel.longID + "/webhooks",
                Array<WebhookObject>::class.java)
        var webhook : Webhook? = null
        for(value in webhookObjects){
            webhookList += arrayOf(Webhook(bot.client, value.name, Utils.convertToLong(value.id)!!, bot.client.getChannelByID(Utils.convertToLong(value.channel_id)!!), bot.client.getUserByID(Utils.convertToLong(value.user.id)!!), value.avatar, value.token))
        }
        for(value in webhookList){
            if(value.defaultName == name){
                webhook = value
            }
        }
        if(webhook == null){
            val name2 = createWebhook(channel, "bot", "https://www.shareicon.net/download/128x128//2017/06/21/887435_logo_512x512.png") //Default Webhook name. Icon is Discord Logo
            name2.changeDefaultAvatar("https://www.shareicon.net/download/128x128//2017/06/21/887435_logo_512x512.png")
        }
        else {
            val jsonObject = jsonBuilder(webhook, "null")
            Utils.writeJSONToFile("/config/tmp/webhook.tmp", jsonObject)
        }
    }
          println("Reading webhook from File.")
          val json = Utils.readJSONFromFile("/config/tmp/webhook.tmp")
          println("JSON Object Accepted")
          val webhook : Webhook = Webhook(bot.client, json.getString("name"), json.getLong("id"), bot.client.getChannelByID(json.getLong("channel_id")), bot.client.getUserByID(json.getJSONObject("user").getLong("id")), json.getString("avatar"), json.getString("token"))
          println("Read webhook")
          File("$s/config/tmp/webhook.tmp").delete()
          println("Deleted webhook file")
          return webhook
    }

  fun defaultWebhook(bot : Bot, channel : IChannel) : Webhook{
    return getByName(bot, channel, "bot")!!
  }
}
