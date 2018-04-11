package com.gg.main.players;

public class APairOfPlayer {

	private Player player1;
	private Player player2;
	
	public APairOfPlayer(Player p1, Player p2) {
		this.player1 = p1;
		this.player2 = p2;
	}

	public Player getPlayer1() {
		return player1;
	}

	public Player getPlayer2() {
		return player2;
	}

	// Deprecated (détruit d'objet)
	public void reversePlayers() {
		Player tmp = player1;
		player1 = player2;
		player2 = tmp;		
	}
	
	public APairOfPlayer getReversedPlayers() {
		return new APairOfPlayer(player2 , player1);
	}
}
