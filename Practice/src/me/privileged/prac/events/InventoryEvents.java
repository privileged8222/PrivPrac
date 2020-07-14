package me.privileged.prac.events;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;

import me.privileged.prac.data.PlayerData;
import me.privileged.prac.main.Main;

public class InventoryEvents implements Listener{

	@EventHandler
	public void onInventoryClickEvent(InventoryClickEvent event) {
		PlayerData playerData = Main.getInstance().getPlayerDataManager().get((Player) event.getWhoClicked());
		switch (event.getClickedInventory().getTitle()) {
		case "container.inventory": // default minecraft inventory title
			if (!(playerData.isInGame())) {
				event.setCancelled(true);
			}
			break;
		}
	}
	
}
