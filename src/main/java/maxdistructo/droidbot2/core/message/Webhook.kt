package maxdistructo.droidbot2.core.message

/*
This code borrows some of the Webhook handling code from sx.blah.discord.handle.impl.obj.Guild.java
The above mentioned code was released under a GNU Lesser General Public License at https://github.com/Discord4J/Discord4J
The only modification of this code was a convert to Kotlin.
*/

import sx.blah.discord.handle.impl.obj.Webhook
import sx.blah.discord.handle.obj.*

object Webhook{

fun createWebhook(channel : IChannel, name: String, avatar : String){
  channel.createWebhook(name, avatar)
}

/*Change accepts the following values

*/
fun name(channel : IChannel, name : String, newName : String){
  val webhook = channel.getWebhook(name) [0]
  webhook.changeDefaultName(newName)
}

fun avatar(channel : IChannel, name : String, avatar : String){
  val webhook = channel.getWebhook(name) [0]
  webhook.changeDefaultAvatar(newName)
}
  fun send(channel : IChannel, name : String){
   val webhook = getByName(channel, name)
   Unirest.post(DiscordEndpoints.WEBHOOKS + webhook.id + "/" + webhook.token)
    .
  }
fun getByName(channel : IChannel, name : String) : Webhook{
  var webhookList = listOf<Webhook>()
  val webhooks = RequestBuffer.request(() ->{
            WebhookObject[] response = ((DiscordClientImpl) client).REQUESTS.GET.makeRequest(
						DiscordEndpoints.GUILDS + getStringID() + "/webhooks",
						WebhookObject[].class);
    if(response != null){
      for(webhookObject in response){
         webhookList += listOf(Webhook(BaseBot.bot.client, webhookObject.name, webhookObject.id, BaseBot.bot.client.getChannelByID(webhookObject.channel_id), BaseBot.bot.client.getUserByID(webhookObject.user.id), webhookObject.avatar, webhookObject.token))
      }
    }
  })

  }
  
  var webhook : Webhook = null
  
  //TODO Insert convert to List<JSONObject> in variable webhookList
  
  for(value in webhookList){
    if(value.getString("name") == name){
      webhook = Webhook(BaseBot.bot.client, value.getString("name"), value.getLong("id"), BaseBot.bot.client.getGuildByID(value.getLong("channel_id")), BaseBot.bot.client.getOurUser(), value.getString("avatar"), value.getString("token"))
    }
  }
  
  
}
    
}
