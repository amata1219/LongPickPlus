package longpick.plus;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import org.bukkit.ChatColor;
import org.bukkit.block.Block;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabExecutor;
import org.bukkit.plugin.java.JavaPlugin;

public class LongPickPlus extends JavaPlugin {

	public static LongPickPlus plugin;
	public static MainCommand mc;
	public static Config config;
	
	public HashMap<String, TabExecutor> command;
	public HashMap<String, Block> changer;
	public String permission = "longpick.plus.longpick";
	
	public void onEnable(){
		plugin = this;
		mc = new MainCommand(this);
		config = new Config(this);
		config.saveDefaultConfig();
		command = new HashMap<String, TabExecutor>();
		command.put("longpick+", new MainCommand(plugin));
		changer = new HashMap<String, Block>();
		getServer().getPluginManager().registerEvents(new EventListeners(), this);
		info("LongPick+ is enabled!");
		
	}
	public void onDisable(){
		info("LongPick+ is disabled!");
	}
	
	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args){
		return this.command.get(command.getName()).onCommand(sender, command, label, args);
	}
	
	public void info(String s){
		getLogger().info(s);
	}
	
	public URL getTopicURL(){
		URL url = null;
		try{
		  url = new URL("http://forum.minecraftuser.jp/viewtopic.php?f=38&t=33924");
		}catch(MalformedURLException e){
		  info(ChatColor.GRAY + "URLが正常に読み込めませんでした(" + url + ")。");
		}
		return url;
	}
}
