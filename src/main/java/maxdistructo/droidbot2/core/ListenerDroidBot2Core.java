package maxdistructo.droidbot2.core;

import static maxdistructo.droidbot2.core.ModuleDroidBot2Core.prefix;

public class ListenerDroidBot2Core{

@EventListener
public static void onMessageRecievedEvent(MessageRecievedEvent event){

IMessage message = event.getMessage();
       IGuild guild = message.getGuild();
       IUser author = message.getAuthor();
       IUser mentioned = Utils.getMentionedUser(message);
       String content = message.getContent();
       Object messageContent[] = content.split(" ");
       if(messageContent(0).equals(prefix + "help")){
       Help.onHelpCommand(message);
       }
       
       

}

}
