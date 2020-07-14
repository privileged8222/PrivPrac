package me.privileged.prac.manager;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.inventory.ItemStack;

import me.privileged.prac.ladder.Ladder;
import me.privileged.prac.ladder.LadderKitType;
import me.privileged.prac.ladder.LadderMode;
import me.privileged.prac.main.Main;
import me.privileged.prac.utils.InventoryUtils;
import net.md_5.bungee.api.ChatColor;

public class LadderManager {

	private ArrayList<Ladder> ladders;
	
	public LadderManager() {
		this.ladders = new ArrayList<>();
		this.loadLadders();
	}
	
	private void loadLadders() {
		List<String> listOfLadders = Main.getInstance().getConfigManager().getLadders().getStringList("ladders");
		for (String currentLadderName : listOfLadders) {
			ItemStack display = null;
			int uid = Integer.valueOf(Main.getInstance().getConfigManager().getLadders().getString("ladders." + currentLadderName + ".uid"));
			LadderMode mode = interpretLadderMode(Main.getInstance().getConfigManager().getLadders().getString("ladders." + currentLadderName + ".mode"));
			LadderKitType kitType = interpretLadderKitType(Main.getInstance().getConfigManager().getLadders().getString("ladders." + currentLadderName + ".kittype"));
			try {
				display = InventoryUtils.itemStackArrayFromBase64(Main.getInstance().getConfigManager().getLadders().getString("ladders." + currentLadderName + ".displayitem"))[0];
				ladders.add(new Ladder(currentLadderName, mode, kitType, display, uid));
			} catch (IOException e) {
				Main.getInstance().getInstance().log(ChatColor.RED + "Failed to read display item for ladder " + currentLadderName + ", skipping");
			}
		}
	}
	
	public Ladder get(int uid) {
		for (Ladder ladder : this.ladders) {
			if (ladder.getUid() == uid) {
				return ladder;
			} else {
				continue;
			}
		}
		return null;
	}
	
	public Ladder get(String name) {
		for (Ladder ladder : this.ladders) {
			if (ladder.getFriendlyName().equalsIgnoreCase(name)) {
				return ladder;
			} else {
				continue;
			}
		}
		return null;
	}
	
	public void add (Ladder ladder) {
		if (!(ladder == null)) {
			this.ladders.add(ladder);
		}
	}
	
	public void create(String ladderName, LadderMode mode, ItemStack display) {
		Bukkit.broadcastMessage("lol");
		int uid = getLatestLadder() + 1;
		List<String> listOfLadders = Main.getInstance().getConfigManager().getLadders().getStringList("ladders");
		listOfLadders.add(String.valueOf(ladderName));
		Main.getInstance().getConfigManager().getLadders().set("ladders", listOfLadders);
		String convertedMode = interpretLadderMode(mode);
		Main.getInstance().getConfigManager().getLadders().set("ladder." + ladderName + ".mode", convertedMode);
		String convertedKitType = "EMPTY";
		Main.getInstance().getConfigManager().getLadders().set("ladder." + ladderName + ".kittype", convertedKitType);
		String convertedDisplay = InventoryUtils.itemStackArrayToBase64(display);
		Main.getInstance().getConfigManager().getLadders().set("ladder." + ladderName + ".displayitem", convertedDisplay);
		String convertedId = String.valueOf(uid);
		Main.getInstance().getConfigManager().getLadders().set("ladder." + ladderName + ".uid", convertedId);
		Main.getInstance().getConfigManager().saveLadders();
		Main.getInstance().getConfigManager().reloadLadders();
		add(new Ladder(ladderName, mode, LadderKitType.EMPTY, display, uid));
	}
	
	public void remove(int uid) {
		for (Ladder ladder : this.ladders) {
			if (ladder.getUid() == uid) {
				this.ladders.remove(ladder);
			} else {
				continue;
			}
		}
	}
	
	public void remove(String name) {
		for (Ladder ladder : this.ladders) {
			if (ladder.getFriendlyName().equalsIgnoreCase(name)) {
				this.ladders.remove(ladder);
			} else {
				continue;
			}
		}
	}
	
	private LadderMode interpretLadderMode(String mode) {
		LadderMode finalReturn = LadderMode.REGULAR;
		switch (mode) {
		case "REGULAR":
			finalReturn = LadderMode.REGULAR;
			break;
		case "SUMO":
			finalReturn = LadderMode.SUMO;
			break;
		case "THIMBLE":
			finalReturn = LadderMode.THIMBLE;
			break;
		}
		
		return finalReturn;
	}
	
	private String interpretLadderMode(LadderMode mode) {
		String finalReturn = "";
		switch (mode) {
		case REGULAR:
			finalReturn = "REGULAR";
			break;
		case SUMO:
			finalReturn = "SUMO";
			break;
		case THIMBLE:
			finalReturn = "THIMBLE";
			break;
		}
		
		return finalReturn;
	}
	
	private LadderKitType interpretLadderKitType(String kitType) {
		LadderKitType finalReturn = LadderKitType.EMPTY;
		switch (kitType) {
		case "COMPLETE":
			finalReturn = LadderKitType.COMPLETE;
			break;
		case "EMPTY":
			finalReturn = LadderKitType.EMPTY;
			break;
		}
		
		return finalReturn;
	}
	
	private String interpretLadderKitType(LadderKitType kitType) {
		String finalReturn = "";
		switch (kitType) {
		case COMPLETE:
			finalReturn = "COMPLETE";
			break;
		case EMPTY:
			finalReturn = "EMPTY";
			break;
		}
		
		return finalReturn;
	}
	
	private int getLatestLadder() {
		int counter = 0;
		for (String ladder : Main.getInstance().getConfigManager().getLadders().getStringList("ladders")) {
			counter++;
		}
		return counter;
	}
	
}
