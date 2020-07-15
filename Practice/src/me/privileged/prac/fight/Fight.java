package me.privileged.prac.fight;

import org.bukkit.entity.Player;

import me.privileged.prac.arena.Arena;

public class Fight {

	private Player p1;
	private Player p2;
	private Arena arena;
	private FightState state;
	private FightEndType endType;
	
	public Fight(Player p1, Player p2, Arena arena, FightState state, FightEndType endType) {
		this.setP1(p1);
		this.setP2(p2);
		this.setArena(arena);
		this.setState(state);
		this.setEndType(endType);
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
	
}
