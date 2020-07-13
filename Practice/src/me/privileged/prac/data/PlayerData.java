package me.privileged.prac.data;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import me.privileged.prac.enums.PlayerGameState;
import me.privileged.prac.main.Main;
import net.md_5.bungee.api.ChatColor;

public class PlayerData {
	
	private final Main main;
	private final String uuid;
	private PlayerGameState gameState;
	private File dataFile;
	private FileConfiguration dataCfg;
	
	public PlayerData(Main main, String uuid) {
		this.main = main;
		this.uuid = uuid;
		this.gameState = PlayerGameState.NONE; 
		File filePath = new File(Main.getInstance().getDataFolder().getAbsolutePath() + "/players/");
		this.dataFile = new File(filePath, uuid + ".yml");
		
		if (!(this.dataFile.exists())) {
			try {
				this.dataFile.createNewFile();
			} catch (IOException e) {
				Main.getInstance().log(ChatColor.RED + "Failed to create player data file");
			}
		}
		
		this.dataCfg = YamlConfiguration.loadConfiguration(this.dataFile);
	}
	
	public PlayerGameState getGameSate() {
		return this.gameState;
	}
	
	public void setGameState(PlayerGameState gameState) {
		this.gameState = gameState;
	}
	
	public void saveDataToFile() {
		
	}
	
}
