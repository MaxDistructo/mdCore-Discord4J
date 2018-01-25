[![](https://jitpack.io/v/MaxDistructo/droidbot2core.svg)](https://jitpack.io/#MaxDistructo/droidbot2core)

# DroidBot2Core

This is a core library for droidbot2 and can be used with any Discord4J bot instance

# Modules:

All modules that use DroidBot2-Core must implement the following methods:

1. A Help Section that integrates into the Help.java class(Unreleased. Will be released soon.) through the use of
```java
Help.addCommand(prefix + "commandName Usage: " + prefix + "commandName @User <#channel> \n This commmand is an example command");
Help.addModCommand(); //Same as above but for Moderator+ commands.
Help.addAdminCommand(); //Same as above but for Admin+ commands.
```
Or by directly adding your Help Command into the JSONArray read by the Config.readHelp(), Config.readModHelp(), and Config.readAdminHelp().

2. Contains the following statement in your module enable section. An example of this is below. This code will make sure that DroidBot2-Core is in the class path and that you have access to the prefix for your commands to follow.
```java
public boolean enable(IDiscordClient dclient) {
		try {
 			Class.forName( "maxdistructo.droidbot2.core.ModuleDroidBot2Core" ); //Checks for droidbot2-core to be in classpath
			prefix = Utils.readPrefix(); //Require config file for prefix
		} catch( Exception e ) {
			e.printStackTrace();
			return false;
		}
		client = dclient;
		dispatcher = client.getDispatcher();
		dispatcher.registerListener(new MessageHandler());
		return true;
	}
```
3. You must credit me as the author of this code if you modify any of it or distribute it as a part of your Module/Discord Bot

Other examples of modules can be found in the DroidBot2-Plugins repository.

# License

1. I must be credited for making this code.
2. You may distribute, modify, or change this code as long as #1 is still followed.
3. This is provided AS IS.
4. By modifying this code, you take full responsibility for anything that is changed.
5. For anything not specified here, please follow the rules of the Creative Commons license found in the License.md
Link to CC License https://creativecommons.org/licenses/by-nc-sa/4.0/

