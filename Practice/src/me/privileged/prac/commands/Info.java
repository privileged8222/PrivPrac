package me.privileged.prac.commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import me.privileged.prac.data.PlayerData;
import me.privileged.prac.main.Main;
import net.md_5.bungee.api.ChatColor;
import me.privileged.prac.utils.BukkitUtils;

public class Info implements CommandExecutor{

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (sender.hasPermission("prac.info") || sender.hasPermission("prac.admin")) {
			if (sender instanceof Player) {
				Player targetPlayer;
				if (args.length <= 0) {
					targetPlayer = (Player) sender;
				} else {
					if (!(Bukkit.getPlayer(args[0]) == null)) {
						targetPlayer = Bukkit.getPlayer(args[0]);
					} else {
						sender.sendMessage(ChatColor.RED + "Player '" + args[0] + "' cannot be found.");
						return true;
					}

				}
				
				PlayerData playerData = Main.getInstance().getPlayerDataManager().get(targetPlayer);
				String inGame; String spectating; String queuing;
				inGame = playerData.isInGame() ? "In Game" : "Not In Game";
				spectating = playerData.isSpectator() ? "Spectating" : "Not Spectating";
				queuing = playerData.isQueuing() ? "Queuing" : "Not Queuing";
				sender.sendMessage(" ");
				sender.sendMessage(ChatColor.GOLD + playerData.getPlayer().getName() + ChatColor.YELLOW + "'s Info");
				sender.sendMessage(ChatColor.BLUE + "" + ChatColor.STRIKETHROUGH + "" + ChatColor.BOLD + "----------------------------");
				if (playerData.isInGame()) {((Player) sender).spigot().sendMessage(BukkitUtils.buildTextComponent("&6 * &e" + inGame, "&eClick to &6Spectate", "/spec " + playerData.getPlayer().getName()));}
				else {((Player) sender).spigot().sendMessage(BukkitUtils.buildTextComponent("&6 * &e" + inGame, "&eClick to &6Duel", "/duel " + playerData.getPlayer().getName()));}
				sender.sendMessage(ChatColor.GOLD + " * " + ChatColor.YELLOW + spectating);
				sender.sendMessage(ChatColor.GOLD + " * " + ChatColor.YELLOW + queuing);
				sender.sendMessage(ChatColor.BLUE + "" + ChatColor.STRIKETHROUGH + "" + ChatColor.BOLD + "----------------------------");
				sender.sendMessage(" ");
			} else {
				sender.sendMessage(ChatColor.RED + "Players only.");
			}
		} else {
			sender.sendMessage(ChatColor.RED + "No permission.");
		}
		return true;
	}

}
