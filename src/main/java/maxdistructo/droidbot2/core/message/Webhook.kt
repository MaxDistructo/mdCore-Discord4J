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

    
}
