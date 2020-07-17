package me.privileged.prac.fight;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;

import me.privileged.prac.arena.Arena;
import me.privileged.prac.data.PlayerData;
import me.privileged.prac.enums.PlayerGameState;
import me.privileged.prac.ladder.Ladder;
import me.privileged.prac.main.Main;
import net.md_5.bungee.api.ChatColor;

public class Fight {

	private Player p1;
	private Player p2;
	private Arena arena;
	private FightState state;
	private FightEndType endType;
	private int fightId;
	private int runningTime;
	private Player winner;
	private Player loser;
	
	public Fight(Player p1, Player p2, Ladder ladder, Arena arena, FightState state, FightEndType endType) {
		this.setP1(p1);
		this.setP2(p2);
		this.setArena(arena);
		this.setState(state);
		this.setEndType(endType);
		this.setRunningTime(0);
	}

	public Player getP1() {
		return p1;
	}

	public void setP1(Player p1) {
		this.p1 = p1;
	}

	public Player getP2() {
		return p2;
	}

	public void setP2(Player p2) {
		this.p2 = p2;
	}

	public Arena getArena() {
		return arena;
	}

	public void setArena(Arena arena) {
		this.arena = arena;
	}

	public FightState getState() {
		return state;
	}

	public void setState(FightState state) {
		this.state = state;
	}

	public FightEndType getEndType() {
		return endType;
	}

	public void setEndType(FightEndType endType) {
		this.endType = endType;
	}
	
	@SuppressWarnings("deprecation")
	public void start(Arena arena) {
		PlayerData p1Data = Main.getInstance().getPlayerDataManager().get(p1); PlayerData p2Data = Main.getInstance().getPlayerDataManager().get(p2);
		p1Data.setGameState(PlayerGameState.WARMUP); p2Data.setGameState(PlayerGameState.WARMUP);
		p1.teleport(arena.getSpawn1()); p2.teleport(arena.getSpawn2());
		int id = Main.getInstance().getServer().getScheduler().scheduleAsyncRepeatingTask(Main.getInstance(), new Runnable() {
			int timer = 5;
			@Override
			public void run() {
				if (timer == 0) {
					p1.sendMessage(ChatColor.YELLOW + "The match has " + ChatColor.GOLD + "started");
					p2.sendMessage(ChatColor.YELLOW + "The match has " + ChatColor.GOLD + "started");
					return;
				}
				p1.sendMessage(ChatColor.GOLD + String.valueOf(timer) + ChatColor.YELLOW + " Seconds until math starts.");
				p2.sendMessage(ChatColor.GOLD + String.valueOf(timer) + ChatColor.YELLOW + " Seconds until math starts.");
				timer-=1;
			}
			
		}, 0, 20);
		
		int id2 = Main.getInstance().getServer().getScheduler().scheduleSyncRepeatingTask(Main.getInstance(), new Runnable() {
			
			@Override
			public void run() {
				Main.getInstance().getServer().getScheduler().cancelTask(id);
				p1Data.setGameState(PlayerGameState.INGAME); p2Data.setGameState(PlayerGameState.INGAME);
				setRunningTime(getRunningTime() + 1);
			}
			
		}, 20 * 6, 20);
		setFightId(id);
	}
	
	@SuppressWarnings("deprecation")
	public void stop() {
		PlayerData winnderData = Main.getInstance().getPlayerDataManager().get(getWinner()); PlayerData loserData = Main.getInstance().getPlayerDataManager().get(getLoser());
		winnderData.setGameState(PlayerGameState.WON); loserData.setGameState(PlayerGameState.LOST);
		Main.getInstance().getServer().getScheduler().cancelTask(getFightId());
		Main.getInstance().getServer().getScheduler().scheduleAsyncDelayedTask(Main.getInstance(), new Runnable() {

			@Override
			public void run() {
				winnderData.setGameState(PlayerGameState.NONE); loserData.setGameState(PlayerGameState.NONE);
				Location spawn = new Location(Main.getInstance().getServer().getWorld("world"), 0,0,0);
				spawn.setX(Double.valueOf(Main.getInstance().getConfigManager().getGlobals().getStringList("spawn.location").get(0)));
				spawn.setY(Double.valueOf(Main.getInstance().getConfigManager().getGlobals().getStringList("spawn.location").get(1)));
				spawn.setZ(Double.valueOf(Main.getInstance().getConfigManager().getGlobals().getStringList("spawn.location").get(2)));
				spawn.setWorld(Main.getInstance().getServer().getWorld(Main.getInstance().getConfigManager().getGlobals().getString("spawn.world")));
				getWinner().teleport(spawn); getLoser().teleport(spawn);
			}
			
		}, 3 * 20);
	}

	public int getFightId() {
		return fightId;
	}

	public void setFightId(int fightId) {
		this.fightId = fightId;
	}

	public int getRunningTime() {
		return runningTime;
	}

	public void setRunningTime(int runningTime) {
		this.runningTime = runningTime;
	}

	public Player getWinner() {
		return winner;
	}

	public void setWinner(Player winner) {
		this.winner = winner;
	}

	public Player getLoser() {
		return loser;
	}

	public void setLoser(Player loser) {
		this.loser = loser;
	}
}
