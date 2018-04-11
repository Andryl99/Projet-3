package com.gg.main.games;
import com.gg.main.EndGameState;
import com.gg.main.players.Player;

public abstract class Game {

	protected int solutionLength;
	protected int nbTurns;
	protected int turnCounter;
	protected Player attacker;
	protected Player defensor;
	protected String solution; // Solution du jeu
	protected String answer; // Donn�e par le defenseur +-=+
	protected String proposition; // Faite par l'attaquant 4549
	
	public Game(int nbTurns, int solutionLength, Player attacker, Player defensor) {
		
		this.nbTurns = nbTurns;
		this.solutionLength = solutionLength;
		this.attacker = attacker;
		this.defensor = defensor;
		this.turnCounter = 0;
	}
	
	public abstract void defensorSelectSolution();

	protected abstract void attackerPlay();

	protected abstract void defensorAnswer();
	
	public EndGameState stateOfGame() {
		if (proposition.equals(solution))
			return EndGameState.ATTACKERWIN;
		else if (!(proposition.equals(solution)) && (this.turnCounter == this.nbTurns))
			return EndGameState.DEFENSORWIN;
		else
			return EndGameState.NOWINNER;
	}

	public boolean nextTurn() {
		this.attackerPlay();
		this.defensorAnswer();
		turnCounter++;
		return (proposition.equals(solution) || turnCounter == nbTurns);
	}
	
	public void reset() {
		this.turnCounter = 0;
		this.solution = "";
		this.answer = "";
		this.proposition = "";
	}
	
	public Player getAttacker() {
		return attacker;
	}
	
	public Player getDefensor() {
		return defensor;
	}
	
	public int getTurnCounter() {
		return turnCounter;
	}
}
