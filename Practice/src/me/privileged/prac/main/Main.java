package me.privileged.prac.main;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitTask;

import me.privileged.prac.commands.Info;
import me.privileged.prac.commands.Prac;
import me.privileged.prac.events.BlockBreakPlaceEvent;
import me.privileged.prac.events.InventoryEvents;
import me.privileged.prac.events.PlayerEvents;
import me.privileged.prac.events.PlayerJoinLeaveEvent;
import me.privileged.prac.events.WorldEvents;
import me.privileged.prac.manager.ConfigManager;
import me.privileged.prac.manager.LadderManager;
import me.privileged.prac.manager.PlayerDataManager;
import me.privileged.prac.tasks.KeepDayTask;
import me.privileged.prac.tasks.ScoreboardTask;

public class Main extends JavaPlugin{

	private static Main instance;
	private PlayerDataManager playerDataManager;
	private ConfigManager configManager;
	private LadderManager ladderManager;
	
	public void log(String msg) {
		Bukkit.getServer().getConsoleSender().sendMessage(msg);
	}
		
	@SuppressWarnings("static-access")
	public void onEnable() {
		this.instance = this;
		log(ChatColor.GOLD + "PrivPrac" + ChatColor.YELLOW + " Has been ENABLED");
		setup();
	}
	
	public void onDisable() {
		log(ChatColor.GOLD + "PrivPrac" + ChatColor.YELLOW + " Has been DISABLED");
	}
	
	public static Main getInstance() {
		return instance;
	}
	
	private void setup() {
		this.registerListeners();
		this.setupManagers();
		this.setupCommands();
		this.registerTasks();
	}

	private void registerListeners() {
		this.getServer().getPluginManager().registerEvents(new PlayerJoinLeaveEvent(), this);
		this.getServer().getPluginManager().registerEvents(new BlockBreakPlaceEvent(), this);
		this.getServer().getPluginManager().registerEvents(new PlayerEvents(), this);
		this.getServer().getPluginManager().registerEvents(new WorldEvents(), this);
		this.getServer().getPluginManager().registerEvents(new InventoryEvents(), this);
	}
	
	private void setupCommands() {
		this.getCommand("info").setExecutor(new Info());
		this.getCommand("prac").setExecutor(new Prac());
	}
	
	@SuppressWarnings("unused")
	public void registerTasks() {
		BukkitTask freezeTask = new KeepDayTask().runTaskTimerAsynchronously(this, 0L, 5L);
		BukkitTask scoreboardTask = new ScoreboardTask().runTaskTimerAsynchronously(this, 0, 0L);
	}
	
	private void setupManagers() {
		this.playerDataManager = new PlayerDataManager();
		this.configManager = new ConfigManager();
		this.ladderManager = new LadderManager();
	}

	public PlayerDataManager getPlayerDataManager() {
		return playerDataManager;
	}

	public ConfigManager getConfigManager() {
		return configManager;
	}

	public LadderManager getLadderManager() {
		return ladderManager;
	}
	
}
