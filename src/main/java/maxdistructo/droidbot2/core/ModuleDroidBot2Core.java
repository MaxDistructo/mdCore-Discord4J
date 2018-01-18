package maxdistructo.droidbot2.core;

public class ModuleDroidBot2Core implements IModule{

  private String moduleName = "DroidBot2 Core";
	private String moduleVersion = "1.0";
	private String moduleMinimumVersion = "2.9.0";
	private String author = "MaxDistructo";
	public static IDiscordClient client;
  public static EventDispatcher dispatcher;
	
	public void disable() {
		
	}

	public boolean enable(IDiscordClient dclient) {
		client = dclient;
		dispatcher = client.getDispatcher();
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
