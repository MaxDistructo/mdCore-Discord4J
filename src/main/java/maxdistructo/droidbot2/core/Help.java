package maxdistructo.droidbot2.core;

import sx.blah.discord.handle.obj.*;
import org.json.*;
import java.lang.StringBuilder;

public class Help{

public static String helpCommand(IMessage message){
IGuild guild = message.getGuild();
IUser user = message.getAuthor();
JSONObject config = Config.readServerConfig(guild);
JSONObject commandsObject = config.getJSONObject("Commands");
JSONArray commandsJSON = commandsObject.getJSONArray("commands");
String[] commands = Utils.toStringArray(commandsJSON);
StringBuilder helpCommandBuilder = new StringBuilder();
int i = 0;
while(i < commands.length){

if(commandsObject.getJSONArray(commands[i]).getBoolean("moderator") && Perms.checkMod(message)){
helpCommandBuilder.append(commands[i] + ": " + commandsObject.getJSONArray(commands[i]).getString("disc") + "\n");
}
else(!commandsObject.getJSONArray(commands[i].getBoolean("moderator") && !Perms.checkMod(message)){
helpCommandBuilder.append(commands[i] + ": " + commandsObject.getJSONArray(commands[i]).getString("disc") + "\n");
}
i++;
}
return helpCommandBuilder.toString();
}

}
