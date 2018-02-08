package com.gg.main.games;
import java.util.List;

import com.gg.main.EndGameState;
import com.gg.main.players.Player;

public abstract class Game {

	protected int solutionLength;
	protected int nbTurns;
	protected int turnCounter;

	protected Player attacker;
	protected Player defensor;


	public Game(int nbTurns, int solutionLength, Player attacker, Player defensor) {
		
		this.nbTurns = nbTurns;
		this.solutionLength = solutionLength;
		this.attacker = attacker;
		this.defensor = defensor;
		this.turnCounter = 0;
	}

	public int getTurnCounter() {
		return turnCounter;
	}

	public void setTurnCounter(int turnCounter) {
		this.turnCounter = turnCounter;
	}

	public void addPlayers(List<Player> players) {
		this.attacker = players.get(0);
		this.defensor = players.get(1);
	}
	
	public abstract void defensorSelectSolution();

	public abstract void attackerPlay();

	public abstract void defensorAnswer();
	
	public abstract boolean nextTurn();
	
	public abstract EndGameState stateOfGame();

}
