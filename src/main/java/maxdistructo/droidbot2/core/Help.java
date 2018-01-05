package maxdistructo.droidbot2.core

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
helpCommandBuilder.append(commands[i] + ": " + commandsObject.getJSONArray(commands[i]).getString("disc") + "\n");
i++;
}
return helpCommandBuilder.toString();

}

}
