package maxdistructo.discord.core

object Command{

  fun onHelp(message : IMessage){
    val user = message.author
    val isAdmin = Perms.checkAdmin(message, user)
    val isMod = Perms.checkMod(message, user)
    val help = Help(message.guild)
    
    
  }

}
