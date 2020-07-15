package me.privileged.prac.manager;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.bukkit.Location;
import org.bukkit.World;

import me.privileged.prac.arena.Arena;
import me.privileged.prac.arena.ArenaMode;
import me.privileged.prac.main.Main;

public class ArenaManager {

	private ArrayList<Arena> arenas;
	
	public ArenaManager() {
		this.arenas = new ArrayList<>(); 
		this.loadArenas();
	}

	private void loadArenas() {
		List<String> listOfArenas = Main.getInstance().getConfigManager().getArenas().getStringList("arenas");
		for (String currentArenaName : listOfArenas) {
			int uid = Integer.valueOf(Main.getInstance().getConfigManager().getArenas().getString("arena." + currentArenaName + ".uid"));
			ArenaMode mode = interpretArenaMode(Main.getInstance().getConfigManager().getArenas().getString("arena." + currentArenaName + ".mode"));
			World world = Main.getInstance().getServer().getWorld(Main.getInstance().getConfigManager().getArenas().getString("arena." + currentArenaName + ".world"));
			List<String> stringSpawnOne = Main.getInstance().getConfigManager().getArenas().getStringList("arena." + currentArenaName + ".spawnone");
			Location spawn1 = new Location(Main.getInstance().getServer().getWorld("world"), 0,0,0);
			spawn1.setX(Double.valueOf(stringSpawnOne.get(0))); spawn1.setY(Double.valueOf(stringSpawnOne.get(1))); spawn1.setZ(Double.valueOf(stringSpawnOne.get(2)));
			spawn1.setWorld(world);
			List<String> stringSpawnTwo = Main.getInstance().getConfigManager().getArenas().getStringList("arena." + currentArenaName + ".spawntwo");
			Location spawn2 = new Location(Main.getInstance().getServer().getWorld("world"), 0,0,0);
			spawn2.setX(Double.valueOf(stringSpawnTwo.get(0))); spawn2.setY(Double.valueOf(stringSpawnTwo.get(1))); spawn2.setZ(Double.valueOf(stringSpawnTwo.get(2)));
			spawn2.setWorld(world);
			add(new Arena(currentArenaName, mode, spawn1, spawn2, world, uid));
		}
	}
	
	public void create(String arenaName, ArenaMode mode) {
		int uid = getLatestArena() + 1;
		List<String> listOfArenas = Main.getInstance().getConfigManager().getArenas().getStringList("arenas");
		listOfArenas.add(String.valueOf(arenaName));
		Main.getInstance().getConfigManager().getArenas().set("arenas", listOfArenas);
		String convertedMode = interpretArenaMode(mode);
		Main.getInstance().getConfigManager().getArenas().set("arena." + arenaName + ".mode", convertedMode);
		List<String> coords1 = Arrays.asList(new String[] {"1", "0", "0"});
		Main.getInstance().getConfigManager().getArenas().set("arena." + arenaName + ".spawnone", coords1);
		List<String> coords2 = Arrays.asList(new String[] {"1", "0", "0"});
		Main.getInstance().getConfigManager().getArenas().set("arena." + arenaName + ".spawntwo", coords2);
		String convertedWorld = "world";
		Main.getInstance().getConfigManager().getArenas().set("arena." + arenaName + ".world", convertedWorld);
		String convertedId = String.valueOf(uid);
		Main.getInstance().getConfigManager().getArenas().set("arena." + arenaName + ".uid", convertedId);
		Main.getInstance().getConfigManager().saveArenas();
		Main.getInstance().getConfigManager().reloadArenas();
		add(new Arena(arenaName, mode, new Location(Main.getInstance().getServer().getWorld("world"), 0,0,0), new Location(Main.getInstance().getServer().getWorld("world"), 0,0,0), Main.getInstance().getServer().getWorld("world"), uid));
	}
	
	public void delete(String name) {
		Main.getInstance().getConfigManager().reloadArenas();
		List<String> listOfLadders = Main.getInstance().getConfigManager().getArenas().getStringList("arenas");
		listOfLadders.remove(String.valueOf(name));
		Main.getInstance().getConfigManager().getArenas().set("arenas", listOfLadders);
		Main.getInstance().getConfigManager().saveArenas();
		Main.getInstance().getConfigManager().reloadArenas();
		remove(name);
	}
	
	public void remove(Arena arena) {
		if (this.arenas.contains(arena)) {
			this.arenas.remove(arena);
		}
	}
	
	public void remove(String name) {
		for (Arena arena : this.arenas) {
			if (arena.getFriendlyName().equalsIgnoreCase(name)) {
				this.arenas.remove(arena);
				return;
			}
		}
	}
	
	public void remove(int uid) {
		for (Arena arena : this.arenas) {
			if (arena.getUid() == uid) {
				this.arenas.remove(arena);
			} else {
				continue;
			}
		}
	}
	
	public void add(Arena arena) {
		if (!(arena == null)) {
			if (!(this.arenas.contains(arena))) {
				this.arenas.add(arena);
			}
		}
	}
	
	public Arena get(String name) {
		for (Arena arena : this.arenas) {
			if (arena.getFriendlyName().equalsIgnoreCase(name)) {
				return arena;
			} else {
				continue;
			}
		}
		return null;
	}
	
	public Arena get(int uid) {
		for (Arena arena : this.arenas) {
			if (arena.getUid() == uid) {
				return arena;
			} else {
				continue;
			}
		}
		return null;
	}
	
	public int getLatestArena() {
		int counter = 0;
		List<String> listOfArenas = Main.getInstance().getConfigManager().getArenas().getStringList("arenas");
		for (@SuppressWarnings("unused") String currentArenaName : listOfArenas) {
			counter++;
		}
		return counter;
	}
	
	public String interpretArenaMode(ArenaMode mode) {
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
	
	public ArenaMode interpretArenaMode(String mode) {
		ArenaMode finalReturn = ArenaMode.REGULAR;
		switch (mode) {
		case "REGULAR":
			finalReturn = ArenaMode.REGULAR;
			break;
		case "SUMO":
			finalReturn = ArenaMode.SUMO;
			break;
		case "THIMBLE":
			finalReturn = ArenaMode.THIMBLE;
			break;
		}
		
		return finalReturn;
	}
	
	public boolean doesExist(String name) {
		for (Arena arena : this.arenas) {
			if (arena.getFriendlyName().equalsIgnoreCase(name)) {
				return true;
			} else {
				continue;
			}
		}
		
		return false;
	}
	
	public ArrayList<String> getCurrentArenas() {
		ArrayList<String> finalReturn = new ArrayList<>();
		for (Arena arena : this.arenas) {
			finalReturn.add(arena.getFriendlyName());
		}
		return finalReturn;
	}
}

