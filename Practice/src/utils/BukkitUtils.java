package utils;

import java.util.HashMap;
import java.util.Iterator;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Score;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.ScoreboardManager;

import me.privileged.prac.data.PlayerData;
import me.privileged.prac.enums.PlayerGameState;
import me.privileged.prac.main.Main;
import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.ComponentBuilder;
import net.md_5.bungee.api.chat.HoverEvent;
import net.md_5.bungee.api.chat.TextComponent;

public class BukkitUtils {

	public static TextComponent buildTextComponent(String message, String hover, String command) {
		TextComponent textCom = new TextComponent(ChatColor.translateAlternateColorCodes('&', message));
		textCom.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, command));
		textCom.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT,
				new ComponentBuilder(ChatColor.translateAlternateColorCodes('&', hover)).create()));
		return textCom;
	}
	
	public static int getPlayersInGame() {
		int counter = 0;
		for (Player player : Main.getInstance().getServer().getOnlinePlayers()) {
			PlayerData playerData = Main.getInstance().getPlayerDataManager().get(player);
			if (playerData.getGameState() == PlayerGameState.INGAME ||
					playerData.getGameState() == PlayerGameState.EVENT_INGAME) {
				counter++;
			}
		}
		return counter;
	}
	
	public static int getUnrankedQueuingPlayers() {
		int counter = 0;
		for (Player player : Main.getInstance().getServer().getOnlinePlayers()) {
			PlayerData playerData = Main.getInstance().getPlayerDataManager().get(player);
			if (playerData.getGameState() == PlayerGameState.QUEUING_UNRANKED) {
				counter++;
			}
		}
		return counter;
	}
	
	public static int getRankedQueuingPlayers() {
		int counter = 0;
		for (Player player : Main.getInstance().getServer().getOnlinePlayers()) {
			PlayerData playerData = Main.getInstance().getPlayerDataManager().get(player);
			if (playerData.getGameState() == PlayerGameState.QUEUING_RANKED) {
				counter++;
			}
		}
		return counter;
	}
	
	public static void createScoreboard(String title, HashMap<Integer, String> map, Player player) {
		ScoreboardManager manager = Bukkit.getScoreboardManager();
		Scoreboard board = manager.getNewScoreboard();
        Objective objective = board.registerNewObjective(title, "");
        objective.setDisplaySlot(DisplaySlot.SIDEBAR);
        objective.setDisplayName(ChatColor.translateAlternateColorCodes('&',title));
	    Iterator it = map.entrySet().iterator();
	    while (it.hasNext()) {
	        HashMap.Entry pair = (HashMap.Entry)it.next();
	        Score score = objective.getScore(ChatColor.translateAlternateColorCodes('&', pair.getValue().toString()));
	        score.setScore((int)pair.getKey());
	        it.remove();
	    }
	    
	    player.setScoreboard(board);
	}
	
}
