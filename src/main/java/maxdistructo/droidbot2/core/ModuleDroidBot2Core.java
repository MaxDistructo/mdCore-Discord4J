package maxdistructo.droidbot2.core;

import sx.blah.discord.api.IDiscordClient;
import sx.blah.discord.api.events.EventDispatcher;
import sx.blah.discord.modules.IModule;

public class ModuleDroidBot2Core implements IModule {

  private String moduleName = "DroidBot2 Core";
	private String moduleVersion = "1.0";
	private String moduleMinimumVersion = "2.9.0";
	private String author = "MaxDistructo";
	public static IDiscordClient client;
	public static EventDispatcher dispatcher;
	public static String prefix;
	public static ListenerDroidBot2Core listener = new ListenerDroidBot2Core();
	
	public void disable() {
		
	}

	public boolean enable(IDiscordClient dclient) {
		if(maxdistructo.droidbot2.core.Client.client != null){
			client = dclient;
		}
		else{
			client = maxdistructo.droidbot2.core.Client.client;
		}
		prefix = Config.readPrefix();
		dispatcher = client.getDispatcher();
		dispatcher.registerListener(listener);
		return true;
	}


	public String getAuthor() {
		return author;
	}

	public String getMinimumDiscord4JVersion() {
		return moduleMinimumVersion;
	}

	public String getName() {
		return moduleName;
	}

	public String getVersion() {
		return moduleVersion;
	}
}
