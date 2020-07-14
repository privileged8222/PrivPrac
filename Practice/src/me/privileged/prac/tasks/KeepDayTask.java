package me.privileged.prac.tasks;

import org.bukkit.Bukkit;
import org.bukkit.scheduler.BukkitRunnable;

import me.privileged.prac.main.Main;

public class KeepDayTask extends BukkitRunnable{
	
	@Override
	public void run() {
		Bukkit.getServer().getWorld("world").setTime(0L);
	}
}
