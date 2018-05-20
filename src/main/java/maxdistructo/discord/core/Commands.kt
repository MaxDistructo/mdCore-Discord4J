package maxdistructo.discord.core

import maxdistructo.discord.core.impl.Help
import maxdistructo.discord.core.message.Message
import sx.blah.discord.handle.obj.IMessage

object Command{

  fun onHelp(message : IMessage){
    val user = message.author
    val isAdmin = Perms.checkAdmin(message)
    val isMod = Perms.checkMod(message)
    val help = Help(message.guild)
    
    if(Perms.checkMod(message)){
      Message.sendDM(message.author, help.adminHelpString("!"))
    }
    else if(Perms.checkMod(message)){
      Message.sendDM(message.author, help.modHelpString("!"))
    }
    else{
      Message.sendDM(message.author, help.helpString("!"))
    }
  }

}
