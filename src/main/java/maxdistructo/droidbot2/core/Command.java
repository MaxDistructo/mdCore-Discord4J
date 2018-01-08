package maxdistructo.droidbot2.core

class Command{
boolean enabled, moderator;
String desc;
}

public Command(){
this.enabled = false;
this.moderator = false;
this.desc = "";
}

public Command(boolean enabled, boolean moderator, String description){
this.enabled = enabled;
this.moderator = moderator;
this.desc = description;
}

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
