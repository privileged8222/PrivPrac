package me.privileged.prac.arena;

import org.bukkit.Location;
import org.bukkit.World;

public class Arena {

	private String friendlyName;
	private ArenaMode mode;
	private Location spawn1;
	private Location spawn2;
	private int uid;
	private World world;
	private ArenaUseType use;
	
	public Arena(String friendlyName, ArenaMode mode, ArenaUseType use, Location spawn1, Location spawn2, World world, int uid) {
		this.friendlyName = friendlyName;
		this.mode = mode;
		this.spawn1 = spawn1;
		this.spawn2 = spawn2;
		this.uid = uid;
		this.world = world;
		this.use = use;
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

	public Location getSpawn1() {
		return spawn1;
	}

	public void setSpawn1(Location spawn1) {
		this.spawn1 = spawn1;
	}

	public Location getSpawn2() {
		return spawn2;
	}

	public void setSpawn2(Location spawn2) {
		this.spawn2 = spawn2;
	}

	public int getUid() {
		return uid;
	}

	public World getWorld() {
		return world;
	}

	public void setWorld(World world) {
		this.world = world;
	}

	public ArenaUseType getUse() {
		return use;
	}

	public void setUse(ArenaUseType use) {
		this.use = use;
	}
	
}
