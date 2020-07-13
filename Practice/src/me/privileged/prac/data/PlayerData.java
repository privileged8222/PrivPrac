package me.privileged.prac.data;

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

	
}
