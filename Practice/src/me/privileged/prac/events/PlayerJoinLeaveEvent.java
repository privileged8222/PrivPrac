package me.privileged.prac.events;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerLoginEvent;

import me.privileged.prac.main.Main;
import net.md_5.bungee.api.ChatColor;

public class PlayerJoinLeaveEvent implements Listener{

	static Main plugin;
	public PlayerJoinLeaveEvent(Main main) {
		plugin = main;
	}
	
	
	@EventHandler
	public void onPlayerLoginEvent(PlayerLoginEvent event) {
		Main.getInstance().getPlayerDataManager().add(event.getPlayer().getUniqueId().toString());
	}
	
}
