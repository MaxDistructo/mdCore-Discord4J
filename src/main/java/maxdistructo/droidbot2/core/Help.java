package maxdistructo.droidbot2.core;


//All DroidBot2-Core Modules will implement into this help code. If you would like to add your own Help code to it, please create a method that adds your Commands into the help array that is read for this command.
public class Help {

public static void onHelpCommand(IMessage message){

String[] helpArray = Config.readHelp(); //Method will return just the array from the Config file.
  if(Perms.checkAdmin(message)){
    String[] modHelpArray = Config.readModHelp(); //In order to allow for mods to have seperate help for mod only commands, this must be done.
    String[] adminHelpArray = Config.readAdminHelp();
    String help1 = Utils.makeNewLineString(helpArray, 1);
    String help2 = Utils.makeNewLineString(modHelpArray, 1);
    String help3 = Utils.makeNewLineString(adminHelpArray, 1);
    Message.sendDM(message.getAuthor(), help1 + "\n Moderator Commands: \n" + help2 + "\n Admin Commands: \n" + help3);
  }
  else if(Perms.checkMod(message)){
    String[] modHelpArray = Config.readModHelp(); //In order to allow for mods to have seperate help for mod only commands, this must be done.
    String help1 = Utils.makeNewLineString(helpArray, 1);
    String help2 = Utils.makeNewLineString(modHelpArray, 1);
    Message.sendDM(message.getAuthor(), help1 + "\n Moderator Commands: \n" + help2);
  }
  else{
    String help = Utils.makeNewLineString(helpArray, 1);
    Message.sendDM(message.getAuthor(), help);
      }
}

  public static void addCommand(String in){ //Call this method to add your commands to the array.

    String[] helpArray = Config.readHelp();
    List<String> help = Arrays.asList(helpArray);
    help.add(in);
    Config.writeHelp(help);

  }
  
  public static void addModCommand(String in){
    
    String[] modHelpArray = Config.readModHelp();
    List<String> help = Arrays.asList(modHelpArray);
    help.add(in);
    Config.writeHelp(help);
  
  }
  
  public static void addAdminCommand(String in){ //Call this method to add your commands to the array.

    String[] helpArray = Config.readHelp();
    List<String> help = Arrays.asList(helpArray);
    help.add(in);
    Config.writeHelp(help);

  }

}
