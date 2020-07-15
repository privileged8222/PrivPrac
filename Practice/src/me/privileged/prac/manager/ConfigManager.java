package me.privileged.prac.manager;

import java.io.File;
import java.io.IOException;

import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import me.privileged.prac.main.Main;
import net.md_5.bungee.api.ChatColor;

public class ConfigManager {
	
	private File globalsFile;
	private FileConfiguration globalsCfg;
	
	private File playersFile;
	private FileConfiguration playersCfg;
	
	private File laddersFile;
	private FileConfiguration laddersCfg;
	
	private File arenasFile;
	private FileConfiguration arenasCfg;
	
	public ConfigManager() {
		this.setup();
	}
	
	private void setup() {
		if (!Main.getInstance().getDataFolder().exists()) {
			Main.getInstance().getDataFolder().mkdir();
		}
		
		globalsFile = new File(Main.getInstance().getDataFolder(), "globals.yml");
		
		if (!globalsFile.exists()) {
			try {
				globalsFile.createNewFile();
			} catch (IOException e) {
				Main.getInstance().log(ChatColor.RED + "failed to create globals.yml file");
			}
		}
		
		globalsCfg = YamlConfiguration.loadConfiguration(globalsFile);
		
		playersFile = new File(Main.getInstance().getDataFolder(), "players.yml");
		
		if (!playersFile.exists()) {
			try {
				playersFile.createNewFile();
			} catch (IOException e) {
				Main.getInstance().log(ChatColor.RED + "failed to create players.yml file");
			}
		}
		
		playersCfg = YamlConfiguration.loadConfiguration(playersFile);

		laddersFile = new File(Main.getInstance().getDataFolder(), "ladders.yml");
		
		if (!laddersFile.exists()) {
			try {
				laddersFile.createNewFile();
			} catch (IOException e) {
				Main.getInstance().log(ChatColor.RED + "failed to create ladders.yml file");
			}
		}
		
		laddersCfg = YamlConfiguration.loadConfiguration(laddersFile);
		
		arenasFile = new File(Main.getInstance().getDataFolder(), "arenas.yml");
		
		if (!arenasFile.exists()) {
			try {
				arenasFile.createNewFile();
			} catch (IOException e){
				Main.getInstance().log(ChatColor.RED + "failed to create arenas.yml file");
			}
		}
		
		arenasCfg = YamlConfiguration.loadConfiguration(arenasFile);
		
	}

	public FileConfiguration getGlobals() {
		return globalsCfg;
	}

	public FileConfiguration getPlayers() {
		return playersCfg;
	}

	public FileConfiguration getLadders() {
		return laddersCfg;
	}
	
	public FileConfiguration getArenas() {
		return arenasCfg;
	}
	
	public void reloadGlobals() {
		this.globalsCfg = YamlConfiguration.loadConfiguration(globalsFile);
	}
	
	public void reloadPlayers() {
		this.playersCfg = YamlConfiguration.loadConfiguration(playersFile);
	}
	
	public void reloadLadders() {
		this.laddersCfg = YamlConfiguration.loadConfiguration(laddersFile);
	}
	
	public void reloadArenas() {
		this.arenasCfg = YamlConfiguration.loadConfiguration(arenasFile);
	}
	
	public void saveGlobals() {
		try {
			this.globalsCfg.save(globalsFile);
		} catch (IOException e) {
			Main.getInstance().log(ChatColor.RED + "failed to save globals.yml");
		}
	}
	
	public void savePlayers() {
		try {
			this.playersCfg.save(playersFile);
		} catch (IOException e) {
			Main.getInstance().log(ChatColor.RED + "failed to save players.yml");
		}
	}
	
	public void saveLadders() {
		try {
			this.laddersCfg.save(laddersFile);
		} catch (IOException e) {
			Main.getInstance().log(ChatColor.RED + "failed to save ladders.yml");
		}
	}
	
	public void saveArenas() {
		try {
			this.arenasCfg.save(arenasFile);
		} catch (IOException e) {
			Main.getInstance().log(ChatColor.RED + "failed to save arenas.yml");
		}
	}
	
}
