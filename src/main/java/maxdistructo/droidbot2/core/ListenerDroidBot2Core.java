package maxdistructo.droidbot2.core;

import sx.blah.discord.handle.obj.*;

import static maxdistructo.droidbot2.core.ModuleDroidBot2Core.prefix;
import sx.blah.discord.api.events.EventSubscriber;
import sx.blah.discord.handle.impl.events.guild.channel.message.MessageReceivedEvent;

public class ListenerDroidBot2Core{

@EventSubscriber
public static void onMessageRecievedEvent(MessageReceivedEvent event){

       IMessage message = event.getMessage();
       IGuild guild = message.getGuild();
       IUser author = message.getAuthor();
       IUser mentioned = Utils.getMentionedUser(message);
       String content = message.getContent();
       Object messageContent[] = content.split(" ");

       if(messageContent[0].equals(prefix + "help")){
       Help.onHelpCommand(message);
       }
       
       

}

}
