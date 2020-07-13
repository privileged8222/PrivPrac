package me.privileged.prac.manager;

import java.util.HashMap;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import me.privileged.prac.data.PlayerData;
import me.privileged.prac.main.Main;
import utils.BukkitUtils;
import utils.ScoreboardUtils;


public class ScoreboardManager {

	public static void setupScoreboard(Player player) {
		PlayerData playerData = Main.getInstance().getPlayerDataManager().get(player);
		
		if (ScoreboardUtils.get(player) == null) {
			return;
		}
		
		ScoreboardUtils scoreboardUtils = ScoreboardUtils.get(player);
		
		String title = "&6&lPRACTICE";
		scoreboardUtils.setTitle(title);
		
		switch (playerData.getGameState()) {
		case NONE:
			for (int i = 8; i <= 15; i++) { scoreboardUtils.removeSlot(i); }
			scoreboardUtils.setSlot(7, "&9&m--------------------");
			scoreboardUtils.setSlot(6, "&6Online: &e" + Bukkit.getServer().getOnlinePlayers().length);
			scoreboardUtils.setSlot(5, "&6In-Game: &e" + BukkitUtils.getPlayersInGame());
			scoreboardUtils.setSlot(4, "&6Queuing: &e" + (BukkitUtils.getUnrankedQueuingPlayers() + BukkitUtils.getRankedQueuingPlayers()));
			scoreboardUtils.setSlot(3, " ");
			scoreboardUtils.setSlot(2, "&7pvp.rest");
			scoreboardUtils.setSlot(1, "&9&m--------------------");
			break;
		}
		
		scoreboardUtils.setScoreboard(player);
	}
	
}
