package me.privileged.prac.manager;

import java.util.HashMap;
import java.util.Map;

import org.bukkit.entity.Player;

import me.privileged.prac.arena.Arena;
import me.privileged.prac.data.PlayerData;
import me.privileged.prac.enums.PlayerGameState;
import me.privileged.prac.fight.Fight;
import me.privileged.prac.fight.FightEndType;
import me.privileged.prac.fight.FightState;
import me.privileged.prac.ladder.Ladder;
import me.privileged.prac.main.Main;

public class FightManager {

	public HashMap<Arena, Fight> fights;
	
	public FightManager() {
		this.fights = new HashMap<>();
	}
	
	public void startNewFight(Player p1, Player p2, Ladder ladder) {
		Arena arena = Main.getInstance().getArenaManager().getArenaForGame();
		if (!(arena == null)) {
			Fight fight = new Fight(p1, p2, ladder, arena, FightState.WARMUP, FightEndType.NONE);
			fight.start(arena);
			add(arena, fight);
		}
	}
	
	public void add(Arena arena, Fight fight) {
		if (!(this.fights.containsValue(fight))) {
			this.fights.put(arena, fight);
		}
	}
	
	public void remove(Arena arena) {
		if (this.fights.containsKey(arena)) {
			this.fights.remove(arena);
		}
	}
	
	public Fight getFight(Player player) {
		for (Map.Entry<Arena, Fight> fight : this.fights.entrySet()) {
			if (fight.getValue().getP1().equals(player) || fight.getValue().getP2().equals(player)) {
				return fight.getValue();
			}
		}

		return null;
	}
	
	public Arena getFightArena(Fight fight) {
		for (Map.Entry<Arena, Fight> mapEntry : this.fights.entrySet()) {
			if (mapEntry.getValue().equals(fight)) {
				return mapEntry.getKey();
			}
		}
		
		return null;
	}
	
}
