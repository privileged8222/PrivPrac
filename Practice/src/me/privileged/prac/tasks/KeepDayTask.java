package me.privileged.prac.tasks;

import org.bukkit.Bukkit;
import org.bukkit.scheduler.BukkitRunnable;

import me.privileged.prac.main.Main;

public class KeepDayTask extends BukkitRunnable{
	
	static Main plugin;
	public KeepDayTask(Main main) {
		plugin = main;
	}
	
	@Override
	public void run() {
		Bukkit.getServer().getWorld("world").setTime(0L);
	}
}
