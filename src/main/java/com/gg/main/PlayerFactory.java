package com.gg.main;
import com.gg.joueurs.Player;

public class PlayerFactory {

	public Player getPlayer()	{
		return createPlayer();
	}

	private Player createPlayer() {
		return new Player();
	}
	
}
