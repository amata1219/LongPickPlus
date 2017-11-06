package longpick.plus;

import org.bukkit.configuration.file.FileConfiguration;

public class Config{

	
	@SuppressWarnings("unused")
	private static FileConfiguration config;
	
	  private LongPickPlus plugin;
	  
	  Config(LongPickPlus plugin){
	    this.plugin = plugin;
	    this.plugin.saveDefaultConfig();
	  }
	  
	  public void loadConfig(){
	    config = this.plugin.getConfig();
	  }
	  
	  public void reloadConfig(){
	    this.plugin.reloadConfig();
	    config = this.plugin.getConfig();
	  }
	  
	  public void saveConfig(){
	    this.plugin.saveConfig();
	  }
	  
	  public void saveDefaultConfig(){
	    this.plugin.saveDefaultConfig();
	  }
	  
	  public FileConfiguration getConfig(){
	    return this.plugin.getConfig();
	  }
}
