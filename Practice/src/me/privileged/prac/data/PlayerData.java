package me.privileged.prac.data;

import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import me.privileged.prac.enums.PlayerGameState;
import me.privileged.prac.fight.Fight;
import me.privileged.prac.main.Main;

public class PlayerData {
	
	private final String uuid;
	private PlayerGameState gameState;
	
	public PlayerData(String uuid) {
		this.uuid = uuid;
		this.gameState = PlayerGameState.NONE;
	}
	
	public PlayerGameState getGameState() {
		return this.gameState;
	}
	 
	public void setGameState(PlayerGameState gameState) {
		this.gameState = gameState;
	}

	public boolean isQueuing() {
		if (getGameState() == PlayerGameState.QUEUING_RANKED ||
				getGameState() == PlayerGameState.QUEUING_UNRANKED) {
			return true;
		} else {
			return false;
		}
	}
	
	public boolean isSpectator() {
		if (getGameState() == PlayerGameState.SPECTATOR ||
				getGameState() == PlayerGameState.EVENT_SPECTATOR) {
			return true;
		} else {
			return false;
		}
	}
	
	public boolean isInGame() {
		if (getGameState() == PlayerGameState.INGAME || 
				getGameState() == PlayerGameState.EVENT_INGAME ||
				getGameState() == PlayerGameState.WARMUP ||
				getGameState() == PlayerGameState.EVENT_WARMUP ||
				getGameState() == PlayerGameState.WON ||
				getGameState() == PlayerGameState.EVENT_WON ||
				getGameState() == PlayerGameState.LOST ||
				getGameState() == PlayerGameState.EVENT_LOST) {
			return true;
		} else {
			return false;
		}
	}
	
	public String getUuid() {
		return this.uuid;
	}
	
	public Player getPlayer() {
		return Bukkit.getPlayer(UUID.fromString(this.uuid));
	}
	
	/*public Fight getCurrentFight() {
		//Main.getInstance().getFightManager().fights
	}*/
}
