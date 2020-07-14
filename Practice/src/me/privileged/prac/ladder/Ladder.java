package me.privileged.prac.ladder;

import org.bukkit.inventory.ItemStack;

public class Ladder {

	private String friendlyName;
	private LadderMode mode;
	private ItemStack display;
	private int uid;
	private LadderKitType kitType;
	
	public Ladder (String friendlyName, LadderMode mode, LadderKitType kitType, ItemStack display, int uid) {
		this.setFriendlyName(friendlyName);
		this.setMode(mode);
		this.display = display;
		this.uid = uid;
		this.kitType = kitType;
	}

	public LadderMode getMode() {
		return mode;
	}

	public void setMode(LadderMode mode) {
		this.mode = mode;
	}

	public String getFriendlyName() {
		return friendlyName;
	}

	public void setFriendlyName(String friendlyName) {
		this.friendlyName = friendlyName;
	}

	public ItemStack getDisplay() {
		return display;
	}

	public void setDisplay(ItemStack display) {
		this.display = display;
	}

	public int getUid() {
		return uid;
	}

	public void setUid(int uid) {
		this.uid = uid;
	}

	public LadderKitType getKitType() {
		return kitType;
	}

	public void setKitType(LadderKitType kitType) {
		this.kitType = kitType;
	}
}
