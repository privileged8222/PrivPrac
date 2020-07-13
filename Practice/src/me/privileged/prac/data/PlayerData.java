package me.privileged.prac.data;

import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import me.privileged.prac.enums.PlayerGameState;

public class PlayerData {
	
	private final String uuid;
	private PlayerGameState gameState;
	
	public PlayerData(String uuid) {
		this.uuid = uuid;
		this.gameState = PlayerGameState.NONE;
	}
	
	public PlayerGameState getGameSate() {
		return this.gameState;
	}
	 
	public void setGameState(PlayerGameState gameState) {
		this.gameState = gameState;
	}

	public String getUuid() {
		return this.uuid;
	}
	
	public Player getPlayer() {
		return Bukkit.getPlayer(UUID.fromString(this.uuid));
	}
}
