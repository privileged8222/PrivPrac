package me.privileged.prac.tasks;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import me.privileged.prac.main.Main;
import me.privileged.prac.manager.ScoreboardManager;

public class ScoreboardTask extends BukkitRunnable{

	static Main plugin;
	public ScoreboardTask(Main main) {
		plugin = main;
	}
	
	@Override
	public void run() {
		for (Player player : Bukkit.getOnlinePlayers()) {
			ScoreboardManager.setupScoreboard(player);
		}
	}
	
}
