package me.privileged.prac.manager;

import java.util.Arrays;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import net.md_5.bungee.api.ChatColor;

public class InventoryManager {

	public static void setupLobbyInventory(Player player) {
		player.getInventory().clear();
		
		ItemStack unranked = new ItemStack(Material.GOLD_SWORD);
		ItemMeta unrankedMeta = unranked.getItemMeta();
		unrankedMeta.setDisplayName(ChatColor.YELLOW + "Queue Unranked");
		unrankedMeta.setLore(Arrays.asList(new String[] {ChatColor.GOLD + "Right click to queue for an unranked fight!"}));
		unranked.setItemMeta(unrankedMeta);
		player.getInventory().setItem(0, unranked);
		
		ItemStack ranked = new ItemStack(Material.DIAMOND_SWORD);
		ItemMeta rankedMeta = ranked.getItemMeta();
		rankedMeta.setDisplayName(ChatColor.BLUE + "Queue Ranked");
		rankedMeta.setLore(Arrays.asList(new String[] {ChatColor.GOLD + "Right click to queue for a ranked fight!"}));
		ranked.setItemMeta(rankedMeta);
		player.getInventory().setItem(1, ranked);
		
		ItemStack party = new ItemStack(Material.GOLD_AXE);
		ItemMeta partyMeta = party.getItemMeta();
		partyMeta.setDisplayName(ChatColor.GOLD + "Create a Party");
		partyMeta.setLore(Arrays.asList(new String[] {ChatColor.GOLD + "Right click to create a party"}));
		party.setItemMeta(partyMeta);
		player.getInventory().setItem(4, party);
		
		ItemStack settings = new ItemStack(Material.REDSTONE_COMPARATOR);
		ItemMeta settingsMeta = settings.getItemMeta();
		settingsMeta.setDisplayName(ChatColor.YELLOW + "Settings");
		settingsMeta.setLore(Arrays.asList(new String[] {ChatColor.GOLD + "Right click to change your settings"}));
		settings.setItemMeta(settingsMeta);
		player.getInventory().setItem(7, settings);
		
		ItemStack kiteditor = new ItemStack(Material.BOOK);
		ItemMeta kiteditorMeta = kiteditor.getItemMeta();
		kiteditorMeta.setDisplayName(ChatColor.GOLD + "Kit Editor");
		kiteditorMeta.setLore(Arrays.asList(new String[] {ChatColor.GOLD + "Right click to edit your kits"}));
		kiteditor.setItemMeta(kiteditorMeta);
		player.getInventory().setItem(8, kiteditor);
	}
	
}
