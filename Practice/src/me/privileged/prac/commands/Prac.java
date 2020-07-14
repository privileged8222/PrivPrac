package me.privileged.prac.commands;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import me.privileged.prac.ladder.LadderMode;
import me.privileged.prac.main.Main;
import me.privileged.prac.utils.CommandUtils;
import net.md_5.bungee.api.ChatColor;

public class Prac implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (args.length > 0) {
			switch (args[0].toLowerCase()) {
			case "ladder":
				if (args.length > 1) {
					switch (args[1].toLowerCase()) {
					case "create":
						if (args.length == 3) {
							Main.getInstance().getLadderManager().create(args[2], LadderMode.REGULAR, new ItemStack(Material.DIAMOND_SWORD));
							Bukkit.broadcastMessage("test");
						}
						break;
					}
				} else {
					sender.sendMessage(" ");
					sender.sendMessage(ChatColor.GOLD + "Help Menu");
					sender.sendMessage(ChatColor.BLUE + "" + ChatColor.STRIKETHROUGH + "" + ChatColor.BOLD + "----------------------------");
					sender.sendMessage(ChatColor.GOLD + " * " + ChatColor.YELLOW + "/prac ladder create : " + ChatColor.GOLD + "Create a ladder");
					sender.sendMessage(ChatColor.BLUE + "" + ChatColor.STRIKETHROUGH + "" + ChatColor.BOLD + "----------------------------");
				}
				break;
			case "setspawn":
				sender.sendMessage(ChatColor.RED + "Command is currently unavailable");
				break;
			case "build":
				{
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
				}
			}
		} else {
			sender.sendMessage(" ");
			sender.sendMessage(ChatColor.GOLD + "Help Menu");
			sender.sendMessage(ChatColor.BLUE + "" + ChatColor.STRIKETHROUGH + "" + ChatColor.BOLD + "----------------------------");
			sender.sendMessage(ChatColor.GOLD + " * " + ChatColor.YELLOW + "/prac ladder : " + ChatColor.GOLD + "Ladder editor");
			sender.sendMessage(ChatColor.GOLD + " * " + ChatColor.YELLOW + "/prac setspawn : " + ChatColor.GOLD + "Sets server spawnpoint");
			sender.sendMessage(ChatColor.GOLD + " * " + ChatColor.YELLOW + "/prac build : " + ChatColor.GOLD + "Toggles build mode");
			sender.sendMessage(ChatColor.BLUE + "" + ChatColor.STRIKETHROUGH + "" + ChatColor.BOLD + "----------------------------");
			sender.sendMessage(" ");
		}
		return true;
	}
}
