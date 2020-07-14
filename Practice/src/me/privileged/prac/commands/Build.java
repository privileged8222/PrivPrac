package me.privileged.prac.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import net.md_5.bungee.api.ChatColor;
import me.privileged.prac.utils.CommandUtils;

public class Build implements CommandExecutor{

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {	
		if (sender instanceof Player) {
			if (sender.hasPermission("practice.build") || sender.hasPermission("practice.admin")) {
				if (CommandUtils.buildPlayers.contains((Player) sender)) {
					CommandUtils.buildPlayers.remove((Player) sender);
					sender.sendMessage(ChatColor.RED + "You are no longer in build mode.");
				} else {
					CommandUtils.buildPlayers.add((Player) sender);
					sender.sendMessage(ChatColor.GREEN + "You are now in build mode.");
				}
			} else {
				sender.sendMessage(ChatColor.RED + "No permission.");
			}
		} else {
			sender.sendMessage(ChatColor.RED + "Players only.");
		}
		
		return true;
	}
}
