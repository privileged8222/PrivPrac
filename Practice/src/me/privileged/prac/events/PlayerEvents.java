package me.privileged.prac.events;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerPickupItemEvent;

import me.privileged.prac.data.PlayerData;
import me.privileged.prac.enums.PlayerGameState;
import me.privileged.prac.main.Main;
import me.privileged.prac.manager.PlayerDataManager;

public class PlayerEvents implements Listener{

	static Main plugin;
	public PlayerEvents(Main main) {
		plugin = main;
	}
	
	@EventHandler
	public void onEntityDamageEvent(EntityDamageEvent event) {
		if (event.getEntity() instanceof Player) {
			Player player = (Player) event.getEntity();
			PlayerData playerData = Main.getInstance().getPlayerDataManager().get(player);
			if (playerData.getGameSate() == PlayerGameState.NONE || 
					playerData.getGameSate() == PlayerGameState.SPECTATOR || playerData.getGameSate() == PlayerGameState.EVENT_SPECTATOR ||
					playerData.getGameSate() == PlayerGameState.WARMUP || playerData.getGameSate() == PlayerGameState.EVENT_WARMUP ||
					playerData.getGameSate() == PlayerGameState.WON || playerData.getGameSate() == PlayerGameState.LOST ||
					playerData.getGameSate() == PlayerGameState.EVENT_WON || playerData.getGameSate() == PlayerGameState.EVENT_LOST) {
				event.setCancelled(true);
			}
		}
	}
	
	@EventHandler
	public void onEntityDamageByEntityEvent(EntityDamageByEntityEvent event) {
		if (event.getEntity() instanceof Player) {
			Player player = (Player) event.getEntity();
			PlayerData playerData = Main.getInstance().getPlayerDataManager().get(player);
			if (playerData.getGameSate() == PlayerGameState.NONE || 
					playerData.getGameSate() == PlayerGameState.SPECTATOR || playerData.getGameSate() == PlayerGameState.EVENT_SPECTATOR ||
					playerData.getGameSate() == PlayerGameState.WARMUP || playerData.getGameSate() == PlayerGameState.EVENT_WARMUP ||
					playerData.getGameSate() == PlayerGameState.WON || playerData.getGameSate() == PlayerGameState.LOST ||
					playerData.getGameSate() == PlayerGameState.EVENT_WON || playerData.getGameSate() == PlayerGameState.EVENT_LOST) {
				event.setCancelled(true);
			}
		}
	}

	@EventHandler
	public void onPlayerDropItemEvent(PlayerDropItemEvent event) {
		Player player = (Player) event.getPlayer();
		PlayerData playerData = Main.getInstance().getPlayerDataManager().get(player);
		if (playerData.getGameSate() == PlayerGameState.NONE || 
				playerData.getGameSate() == PlayerGameState.SPECTATOR || playerData.getGameSate() == PlayerGameState.EVENT_SPECTATOR ||
				playerData.getGameSate() == PlayerGameState.WARMUP || playerData.getGameSate() == PlayerGameState.EVENT_WARMUP ||
				playerData.getGameSate() == PlayerGameState.WON || playerData.getGameSate() == PlayerGameState.LOST ||
				playerData.getGameSate() == PlayerGameState.EVENT_WON || playerData.getGameSate() == PlayerGameState.EVENT_LOST) {
			event.setCancelled(true);
		}
	}
	
	@EventHandler
	public void onPlayerPickupItemEvent(PlayerPickupItemEvent event) {
		Player player = (Player) event.getPlayer();
		PlayerData playerData = Main.getInstance().getPlayerDataManager().get(player);
		if (playerData.getGameSate() == PlayerGameState.NONE || 
				playerData.getGameSate() == PlayerGameState.SPECTATOR || playerData.getGameSate() == PlayerGameState.EVENT_SPECTATOR ||
				playerData.getGameSate() == PlayerGameState.WARMUP || playerData.getGameSate() == PlayerGameState.EVENT_WARMUP ||
				playerData.getGameSate() == PlayerGameState.WON || playerData.getGameSate() == PlayerGameState.LOST ||
				playerData.getGameSate() == PlayerGameState.EVENT_WON || playerData.getGameSate() == PlayerGameState.EVENT_LOST) {
			event.setCancelled(true);
		}
	}
}
