package me.privileged.prac.main;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.plugin.java.JavaPlugin;

import me.privileged.prac.commands.Build;
import me.privileged.prac.events.BlockBreakPlaceEvent;
import me.privileged.prac.events.PlayerJoinLeaveEvent;
import me.privileged.prac.manager.PlayerDataManager;

public class Main extends JavaPlugin{

	private static Main instance;
	private PlayerDataManager playerDataManager;
	
	public void log(String msg) {
		Bukkit.getServer().getConsoleSender().sendMessage(msg);
	}
		
	@SuppressWarnings("static-access")
	public void onEnable() {
		this.instance = this;
		log(ChatColor.DARK_GREEN + "PrivPrac" + ChatColor.GREEN + " Has been ENABLED");
		setup();
	}
	
	public void onDisable() {
		log(ChatColor.DARK_GREEN + "PrivPrac" + ChatColor.GREEN + " Has been DISABLED");
	}
	
	public static Main getInstance() {
		return instance;
	}
	
	private void setup() {
		this.registerListeners();
		this.setupManagers();
		this.setupCommands();
	}

	private void registerListeners() {
		this.getServer().getPluginManager().registerEvents(new PlayerJoinLeaveEvent(this), this);
		this.getServer().getPluginManager().registerEvents(new BlockBreakPlaceEvent(this), this);
	}
	
	private void setupCommands() {
		this.getCommand("build").setExecutor(new Build());
	}
	
	private void setupManagers() {
		this.playerDataManager = new PlayerDataManager();
	}

	public PlayerDataManager getPlayerDataManager() {
		return playerDataManager;
	}
	
}
