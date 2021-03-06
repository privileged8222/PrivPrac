package me.privileged.prac.events;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;

import me.privileged.prac.main.Main;
import me.privileged.prac.utils.CommandUtils;

public class BlockBreakPlaceEvent implements Listener {
	
	@EventHandler
	public void onBlockBreakEvent(BlockBreakEvent event) {
		if (CommandUtils.buildPlayers.contains(event.getPlayer())) {
			return;
		}
		
		event.setCancelled(true);
	}
	
	@EventHandler
	public void onBlockPlaceEvent(BlockPlaceEvent event) {
		if (CommandUtils.buildPlayers.contains(event.getPlayer())) {
			return;
		}
		
		event.setCancelled(true);
	}
	
}
