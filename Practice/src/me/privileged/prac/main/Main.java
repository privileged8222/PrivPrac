package me.privileged.prac.main;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.event.Listener;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

import me.privileged.prac.events.PlayerJoinLeaveEvent;
import me.privileged.prac.manager.PlayerDataManager;

public class Main extends JavaPlugin{

	private static Main instance;
	private PlayerDataManager playerDataManager;
	
	public void log(String msg) {
		Bukkit.getServer().getConsoleSender().sendMessage(msg);
	}
		
	public void onEnable() {
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
	}

	private void registerListeners() {
		this.getServer().getPluginManager().registerEvents((Listener)new PlayerJoinLeaveEvent(), (Plugin)this);
	}
	
	private void setupManagers() {
		this.playerDataManager = new PlayerDataManager();
	}

	public PlayerDataManager getPlayerDataManager() {
		return playerDataManager;
	}
	
}
