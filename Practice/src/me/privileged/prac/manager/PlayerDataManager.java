package me.privileged.prac.manager;

import java.util.HashMap;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import me.privileged.prac.data.PlayerData;
import me.privileged.prac.main.Main;
import net.md_5.bungee.api.ChatColor;

public class PlayerDataManager {

	public static PlayerDataManager instance;	
	private HashMap<String, PlayerData> playerDatas;
	
	
	public PlayerDataManager() {
		this.instance = this;
	}
	
	public static void loadAllPlayerData() {
		
	}
	
	public static void saveAllPlayerData() {
		
	}
	
	public PlayerData get(Player player) {
		return this.playerDatas.get(player.getUniqueId().toString());
	}
	
	public PlayerData get(String uuid) {
		return this.playerDatas.get(uuid);
	}
	
	public void add(Player player) {
		if (!(player.getUniqueId().toString() == null)) {
			this.finalisedAdd(player.getUniqueId().toString());
		}
	}
	
	public void add(String uuid) {
		if (!(uuid == null)) {
			this.finalisedAdd(uuid);
		}
	}
	
	private void finalisedAdd(String uuid) {
		if (this.playerDatas.containsKey(uuid)) {
			Main.getInstance().log(ChatColor.RED + "Player data for: " + uuid + " is already stored, ignoring.");
		} else {
			this.playerDatas.put(uuid, new PlayerData(Main.getInstance(), uuid));
		}
	}
	
	public void remove(Player player) {
		if (!(player.getUniqueId().toString() == null)) {
			this.finalisedRemove(player.getUniqueId().toString());
		}
	}
	
	public void remove(String uuid) {
		if (!(uuid == null)) {
			this.finalisedRemove(uuid);
		}
	}
	
	private void finalisedRemove(String uuid) {
		if (this.playerDatas.containsKey(uuid)) {
			this.playerDatas.remove(uuid);
		} else {
			Main.getInstance().log(ChatColor.RED + "Player data for: " + uuid + " is not stored, ignoring");
		}
	}
}
