package maxdistructo.droidbot2.core.message

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
   Unirest.post(DiscordEndpoints.GATEWAY + "/webhooks/" + webhook.id + "/" + webhook.token)
    .
  }
fun getByName(channel : IChannel, name : String) : IWebhook{
  val webhooks = Unirest.get(DiscordEndpoints.GATEWAY + "/channels/" + channel.longID + "/webhooks").asJson().body.`object`
  
}
    
}
