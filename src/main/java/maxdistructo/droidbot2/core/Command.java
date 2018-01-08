package maxdistructo.droidbot2.core

public class Command{

public static boolean isEnabled(String commandName, IGuild guild){

try{
JSONObject config = Config.readServerConfig(guild);
JSONObject commands = config.getJSONObject("Commands");
JSONObject command = commands.getJSONObject(commandName);
return command.getBoolean("enabled");
}
catch(Exception e){
Message.sendError(e);
}

}

public static boolean getModeratorStatus(String commandName, IGuild guild){

try{
JSONObject config = Config.readServerConfig(guild);
JSONObject commands = config.getJSONObject("Commands");
JSONObject command = commands.getJSONObject(commandName);
return command.getBoolean("moderator");
}
catch(Exception e){
Message.sendError(e);
}

}

}
