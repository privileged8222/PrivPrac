package me.privileged.prac.events;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerLoginEvent;

import me.privileged.prac.main.Main;

public class PlayerJoinLeaveEvent implements Listener{

	@EventHandler
	public void onPlayerLoginEvent(PlayerLoginEvent event) {
		Main.getInstance().getPlayerDataManager().add(event.getPlayer());
	}
	
}
