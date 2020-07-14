package me.privileged.prac.arena;

public class Arena {

	private String friendlyName;
	private ArenaMode mode;
	
	public Arena(String friendlyName, ArenaMode mode) {
		this.setFriendlyName(friendlyName);
		this.setMode(mode);
	}

	public String getFriendlyName() {
		return friendlyName;
	}

	public void setFriendlyName(String friendlyName) {
		this.friendlyName = friendlyName;
	}

	public ArenaMode getMode() {
		return mode;
	}

	public void setMode(ArenaMode mode) {
		this.mode = mode;
	}
	
}
