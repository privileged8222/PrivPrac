package me.privileged.prac.commands;

import java.util.Arrays;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import me.privileged.prac.arena.ArenaMode;
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
							if (!Main.getInstance().getLadderManager().doesExist(args[2])) {
								Main.getInstance().getLadderManager().create(args[2], LadderMode.REGULAR, new ItemStack(Material.DIAMOND_SWORD));
								sender.sendMessage(ChatColor.GREEN + "Ladder '" + args[2] + "' created successfully.");
							} else {
								sender.sendMessage(ChatColor.RED + "Ladder '" + args[2] + "' cannot be created because it already exists.");
							}
						} else {
							sender.sendMessage(ChatColor.RED + "Usage: /prac ladder create <name>");
						}
						break;
					case "delete":
						if (args.length == 3) {
							if (Main.getInstance().getLadderManager().doesExist(args[2])) {
								Main.getInstance().getLadderManager().delete(args[2]);
								sender.sendMessage(ChatColor.GREEN + "Ladder '" + args[2] + "' deleted successfully.");
							} else {
								sender.sendMessage(ChatColor.RED + "Ladder '" + args[2] + "' cannot be deleted because it does not exist.");
							}
						} else {
							sender.sendMessage(ChatColor.RED + "Usage: /prac ladder delete <name>");
						}
						break;
					case "list":
						if (Main.getInstance().getLadderManager().getCurrentLadders().size() > 0) {
							sender.sendMessage(" ");
							sender.sendMessage(ChatColor.GOLD + "Current Ladders");
							sender.sendMessage(ChatColor.BLUE + "" + ChatColor.STRIKETHROUGH + "" + ChatColor.BOLD + "----------------------------");
							for (String currentLadder : Main.getInstance().getLadderManager().getCurrentLadders()) {
								sender.sendMessage(ChatColor.GOLD + " * " + ChatColor.YELLOW + currentLadder);
							}
							sender.sendMessage(ChatColor.BLUE + "" + ChatColor.STRIKETHROUGH + "" + ChatColor.BOLD + "----------------------------");
							sender.sendMessage(" ");
						} else {
							sender.sendMessage(ChatColor.RED + "There are no ladders to show.");
						}
						break;
					}
				} else {
					sender.sendMessage(" ");
					sender.sendMessage(ChatColor.GOLD + "Help Menu");
					sender.sendMessage(ChatColor.BLUE + "" + ChatColor.STRIKETHROUGH + "" + ChatColor.BOLD + "----------------------------");
					sender.sendMessage(ChatColor.GOLD + " * " + ChatColor.YELLOW + "/prac ladder create <name> : " + ChatColor.GOLD + "Create a ladder");
					sender.sendMessage(ChatColor.GOLD + " * " + ChatColor.YELLOW + "/prac ladder delete <name> : " + ChatColor.GOLD + "Delete a ladder");
					sender.sendMessage(ChatColor.GOLD + " * " + ChatColor.YELLOW + "/prac ladder list : " + ChatColor.GOLD + "Lists all ladders");
					sender.sendMessage(ChatColor.BLUE + "" + ChatColor.STRIKETHROUGH + "" + ChatColor.BOLD + "----------------------------");
				}
				break;
			case "setspawn":
				if (sender instanceof Player) {
					Location spawn = ((Player) sender).getLocation();
					String spawnWorld = ((Player) sender).getWorld().getName();
					String spawnX = String.valueOf(spawn.getX()); String spawnY = String.valueOf(spawn.getY()); String spawnZ = String.valueOf(spawn.getZ()); 
					List<String> spawnLocation = Arrays.asList(new String[] {spawnX, spawnY, spawnZ});
					Main.getInstance().getConfigManager().getGlobals().set("spawn.world", spawnWorld);
					Main.getInstance().getConfigManager().getGlobals().set("spawn.location", spawnLocation);
					Main.getInstance().getConfigManager().saveGlobals(); Main.getInstance().getConfigManager().reloadGlobals();
					sender.sendMessage(ChatColor.GREEN + "World spawn set successfully.");
				} else {
					sender.sendMessage(ChatColor.RED + "Players only.");
				}
				break;
			case "build":
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
				break;
			case "arena":
				if (args.length > 1) {
					switch (args[1].toLowerCase()) {
					case "create":
						if (args.length == 3) {
							if (!Main.getInstance().getArenaManager().doesExist(args[2])) {
								Main.getInstance().getArenaManager().create(args[2], ArenaMode.REGULAR);
								sender.sendMessage(ChatColor.GREEN + "Arena '" + args[2] + "' created successfully.");
							} else {
								sender.sendMessage(ChatColor.RED + "Arena '" + args[2] + "' cannot be created because it already exists.");
							}
						} else {
							sender.sendMessage(ChatColor.RED + "Usage: /prac arena create <name>");
						}
						break;
					case "delete":
						if (args.length == 3) {
							if (Main.getInstance().getArenaManager().doesExist(args[2])) {
								Main.getInstance().getArenaManager().delete(args[2]);
								sender.sendMessage(ChatColor.GREEN + "Arena '" + args[2] + "' deleted successfully.");
							} else {
								sender.sendMessage(ChatColor.RED + "Arena '" + args[2] + "' cannot be deleted because it does not exist.");
							}
						} else {
							sender.sendMessage(ChatColor.RED + "Usage: /prac arena delete <name>");
						}
						break;
					case "edit":
						if (args.length > 3) {
							switch (args[3].toLowerCase()) {
							case "setspawn":
								if (sender instanceof Player) {
									if (args.length > 4) {
										switch (args[4].toLowerCase()) {
										case "one":
											if (Main.getInstance().getArenaManager().doesExist(args[2])) {
												Main.getInstance().getArenaManager().setArenaSpawnOne(Main.getInstance().getArenaManager().get(args[2]), ((Player)sender).getLocation());
												sender.sendMessage(ChatColor.GREEN + "Arena '" + args[2] + "' edited successfully.");
											} else {
												sender.sendMessage(ChatColor.RED + "Cannot edit arena '" + args[2] + "' because it does not exist.");
											}
											break;
										case "two":
											if (Main.getInstance().getArenaManager().doesExist(args[2])) {
												Main.getInstance().getArenaManager().setArenaSpawnTwo(Main.getInstance().getArenaManager().get(args[2]), ((Player)sender).getLocation());
												sender.sendMessage(ChatColor.GREEN + "Arena '" + args[2] + "' edited successfully.");
											} else {
												sender.sendMessage(ChatColor.RED + "Cannot edit arena '" + args[2] + "' because it does not exist.");
											}
											break;
										}
									} else {
										sender.sendMessage(ChatColor.RED + "Usage: /prac arena edit <arena> setspawn <one:two>");
									}
								} else {
									sender.sendMessage(ChatColor.RED + "Players only.");
								}
							}
						} else {
							sender.sendMessage(" ");
							sender.sendMessage(ChatColor.GOLD + "Help Menu");
							sender.sendMessage(ChatColor.BLUE + "" + ChatColor.STRIKETHROUGH + "" + ChatColor.BOLD + "----------------------------");
							sender.sendMessage(ChatColor.GOLD + " * " + ChatColor.YELLOW + "/prac arena edit <arena> setspawn <one:two>");
							sender.sendMessage(ChatColor.BLUE + "" + ChatColor.STRIKETHROUGH + "" + ChatColor.BOLD + "----------------------------");
							sender.sendMessage(" ");
						}
						break;
					case "list":
						if (Main.getInstance().getArenaManager().getCurrentArenas().size() > 0) {
							sender.sendMessage(" ");
							sender.sendMessage(ChatColor.GOLD + "Current Arenas");
							sender.sendMessage(ChatColor.BLUE + "" + ChatColor.STRIKETHROUGH + "" + ChatColor.BOLD + "----------------------------");
							for (String currentArena : Main.getInstance().getArenaManager().getCurrentArenas()) {
								sender.sendMessage(ChatColor.GOLD + " * " + ChatColor.YELLOW + currentArena);
							}
							sender.sendMessage(ChatColor.BLUE + "" + ChatColor.STRIKETHROUGH + "" + ChatColor.BOLD + "----------------------------");
							sender.sendMessage(" ");
						} else {
							sender.sendMessage(ChatColor.RED + "There are no arenas to show");
						}
						break;
					}
				} else {
					sender.sendMessage(" ");
					sender.sendMessage(ChatColor.GOLD + "Help Menu");
					sender.sendMessage(ChatColor.BLUE + "" + ChatColor.STRIKETHROUGH + "" + ChatColor.BOLD + "----------------------------");
					sender.sendMessage(ChatColor.GOLD + " * " + ChatColor.YELLOW + "/prac arena create <name> : " + ChatColor.GOLD + "Create a arena");
					sender.sendMessage(ChatColor.GOLD + " * " + ChatColor.YELLOW + "/prac arena delete <name> : " + ChatColor.GOLD + "Delete a arena");
					sender.sendMessage(ChatColor.GOLD + " * " + ChatColor.YELLOW + "/prac arena edit <name> : " + ChatColor.GOLD + "Edit an arena");
					sender.sendMessage(ChatColor.GOLD + " * " + ChatColor.YELLOW + "/prac arena list : " + ChatColor.GOLD + "Lists all arenas");
					sender.sendMessage(ChatColor.BLUE + "" + ChatColor.STRIKETHROUGH + "" + ChatColor.BOLD + "----------------------------");
					sender.sendMessage(" ");
				}
				break;
			case "fight":
				if (args.length > 1) {
					switch (args[1].toLowerCase()) {
					case "start":
						if (args.length == 4) {
							if (Bukkit.getPlayer(args[2]) != null && Bukkit.getPlayer(args[3]) != null) {
								Main.getInstance().getFightManager().startNewFight(Bukkit.getPlayer(args[2]), Bukkit.getPlayer(args[3]), Main.getInstance().getLadderManager().getLadderForGame());
								sender.sendMessage(ChatColor.GREEN + "Fight started successfully.");
							} else {
								sender.sendMessage(ChatColor.RED + "Invalid player.");
							}
						} else {
							sender.sendMessage(" ");
							sender.sendMessage(ChatColor.GOLD + "Help Menu");
							sender.sendMessage(ChatColor.BLUE + "" + ChatColor.STRIKETHROUGH + "" + ChatColor.BOLD + "----------------------------");
							sender.sendMessage(ChatColor.GOLD + " * " + ChatColor.YELLOW + "/prac fight start <player1> <player2> : " + ChatColor.GOLD + "Force start a fight");
							sender.sendMessage(ChatColor.BLUE + "" + ChatColor.STRIKETHROUGH + "" + ChatColor.BOLD + "----------------------------");
							sender.sendMessage(" ");
						}
					}
					break;
				} else {
					sender.sendMessage(" ");
					sender.sendMessage(ChatColor.GOLD + "Help Menu");
					sender.sendMessage(ChatColor.BLUE + "" + ChatColor.STRIKETHROUGH + "" + ChatColor.BOLD + "----------------------------");
					sender.sendMessage(ChatColor.GOLD + " * " + ChatColor.YELLOW + "/prac fight start <player1> <player2> : " + ChatColor.GOLD + "Force start a fight");
					sender.sendMessage(ChatColor.GOLD + " * " + ChatColor.YELLOW + "/prac fight stop <player> : " + ChatColor.GOLD + "Force stop a fight");
					sender.sendMessage(ChatColor.BLUE + "" + ChatColor.STRIKETHROUGH + "" + ChatColor.BOLD + "----------------------------");
					sender.sendMessage(" ");
				}
				break;
			default:
				sender.sendMessage(" ");
				sender.sendMessage(ChatColor.GOLD + "Help Menu");
				sender.sendMessage(ChatColor.BLUE + "" + ChatColor.STRIKETHROUGH + "" + ChatColor.BOLD + "----------------------------");
				sender.sendMessage(ChatColor.GOLD + " * " + ChatColor.YELLOW + "/prac ladder : " + ChatColor.GOLD + "Ladder editor");
				sender.sendMessage(ChatColor.GOLD + " * " + ChatColor.YELLOW + "/prac arena : " + ChatColor.GOLD + "Arena editor");
				sender.sendMessage(ChatColor.GOLD + " * " + ChatColor.YELLOW + "/prac setspawn : " + ChatColor.GOLD + "Sets server spawnpoint");
				sender.sendMessage(ChatColor.GOLD + " * " + ChatColor.YELLOW + "/prac build : " + ChatColor.GOLD + "Toggles build mode");
				sender.sendMessage(ChatColor.BLUE + "" + ChatColor.STRIKETHROUGH + "" + ChatColor.BOLD + "----------------------------");
				sender.sendMessage(" ");
				break;
			}
		} else {
			sender.sendMessage(" ");
			sender.sendMessage(ChatColor.GOLD + "Help Menu");
			sender.sendMessage(ChatColor.BLUE + "" + ChatColor.STRIKETHROUGH + "" + ChatColor.BOLD + "----------------------------");
			sender.sendMessage(ChatColor.GOLD + " * " + ChatColor.YELLOW + "/prac ladder : " + ChatColor.GOLD + "Ladder editor");
			sender.sendMessage(ChatColor.GOLD + " * " + ChatColor.YELLOW + "/prac arena : " + ChatColor.GOLD + "Arena editor");
			sender.sendMessage(ChatColor.GOLD + " * " + ChatColor.YELLOW + "/prac setspawn : " + ChatColor.GOLD + "Sets server spawnpoint");
			sender.sendMessage(ChatColor.GOLD + " * " + ChatColor.YELLOW + "/prac build : " + ChatColor.GOLD + "Toggles build mode");
			sender.sendMessage(ChatColor.BLUE + "" + ChatColor.STRIKETHROUGH + "" + ChatColor.BOLD + "----------------------------");
			sender.sendMessage(" ");
		}
		return true;
	}
}
