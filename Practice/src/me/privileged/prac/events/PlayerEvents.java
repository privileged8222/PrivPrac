package me.privileged.prac.events;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.FoodLevelChangeEvent;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerPickupItemEvent;

import me.privileged.prac.data.PlayerData;
import me.privileged.prac.enums.PlayerGameState;
import me.privileged.prac.main.Main;
import me.privileged.prac.manager.PlayerDataManager;

public class PlayerEvents implements Listener{

	@EventHandler
	public void onEntityDamageEvent(EntityDamageEvent event) {
		if (event.getEntity() instanceof Player) {
			Player player = (Player) event.getEntity();
			PlayerData playerData = Main.getInstance().getPlayerDataManager().get(player);
			if (playerData.getGameState() == PlayerGameState.NONE || 
					playerData.getGameState() == PlayerGameState.SPECTATOR || playerData.getGameState() == PlayerGameState.EVENT_SPECTATOR ||
					playerData.getGameState() == PlayerGameState.WARMUP || playerData.getGameState() == PlayerGameState.EVENT_WARMUP ||
					playerData.getGameState() == PlayerGameState.WON || playerData.getGameState() == PlayerGameState.LOST ||
					playerData.getGameState() == PlayerGameState.EVENT_WON || playerData.getGameState() == PlayerGameState.EVENT_LOST) {
				event.setCancelled(true);
			}
		}
	}
	
	@EventHandler
	public void onEntityDamageByEntityEvent(EntityDamageByEntityEvent event) {
		if (event.getEntity() instanceof Player) {
			Player player = (Player) event.getEntity();
			PlayerData playerData = Main.getInstance().getPlayerDataManager().get(player);
			if (playerData.getGameState() == PlayerGameState.NONE || 
					playerData.getGameState() == PlayerGameState.SPECTATOR || playerData.getGameState() == PlayerGameState.EVENT_SPECTATOR ||
					playerData.getGameState() == PlayerGameState.WARMUP || playerData.getGameState() == PlayerGameState.EVENT_WARMUP ||
					playerData.getGameState() == PlayerGameState.WON || playerData.getGameState() == PlayerGameState.LOST ||
					playerData.getGameState() == PlayerGameState.EVENT_WON || playerData.getGameState() == PlayerGameState.EVENT_LOST) {
				event.setCancelled(true);
			}
		}
	}

	@EventHandler
	public void onPlayerDropItemEvent(PlayerDropItemEvent event) {
		Player player = (Player) event.getPlayer();
		PlayerData playerData = Main.getInstance().getPlayerDataManager().get(player);
		if (playerData.getGameState() == PlayerGameState.NONE || 
				playerData.getGameState() == PlayerGameState.SPECTATOR || playerData.getGameState() == PlayerGameState.EVENT_SPECTATOR ||
				playerData.getGameState() == PlayerGameState.WARMUP || playerData.getGameState() == PlayerGameState.EVENT_WARMUP ||
				playerData.getGameState() == PlayerGameState.WON || playerData.getGameState() == PlayerGameState.LOST ||
				playerData.getGameState() == PlayerGameState.EVENT_WON || playerData.getGameState() == PlayerGameState.EVENT_LOST) {
			event.setCancelled(true);
		}
	}
	
	@EventHandler
	public void onPlayerPickupItemEvent(PlayerPickupItemEvent event) {
		Player player = (Player) event.getPlayer();
		PlayerData playerData = Main.getInstance().getPlayerDataManager().get(player);
		if (playerData.getGameState() == PlayerGameState.NONE || 
				playerData.getGameState() == PlayerGameState.SPECTATOR || playerData.getGameState() == PlayerGameState.EVENT_SPECTATOR ||
				playerData.getGameState() == PlayerGameState.WARMUP || playerData.getGameState() == PlayerGameState.EVENT_WARMUP ||
				playerData.getGameState() == PlayerGameState.WON || playerData.getGameState() == PlayerGameState.LOST ||
				playerData.getGameState() == PlayerGameState.EVENT_WON || playerData.getGameState() == PlayerGameState.EVENT_LOST) {
			event.setCancelled(true);
		}
	}
	
	@EventHandler
	public void onFoodLevelChangeEvent(FoodLevelChangeEvent event) {
		PlayerData playerData = Main.getInstance().getPlayerDataManager().get((Player) event.getEntity());
		if (!(playerData.isInGame())) {
			event.setCancelled(true);
		}
	}
}
