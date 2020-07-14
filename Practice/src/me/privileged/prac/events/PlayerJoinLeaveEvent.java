package me.privileged.prac.events;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerLoginEvent;
import org.bukkit.event.player.PlayerQuitEvent;

import me.privileged.prac.main.Main;
import me.privileged.prac.manager.InventoryManager;
import utils.ScoreboardUtils;

public class PlayerJoinLeaveEvent implements Listener{
	
	@EventHandler
	public void onPlayerLoginEvent(PlayerLoginEvent event) {
		Main.getInstance().getPlayerDataManager().add(event.getPlayer().getUniqueId().toString());
	}
	
	@EventHandler
	public void onPlayerQuitEvent(PlayerQuitEvent event) {
		Main.getInstance().getPlayerDataManager().remove(event.getPlayer().getUniqueId().toString());
	}
	
	@EventHandler
	public void onPlayerJoinEvent(PlayerJoinEvent event) {
		ScoreboardUtils.create(event.getPlayer());
		InventoryManager.setupLobbyInventory(event.getPlayer());
	}
}
