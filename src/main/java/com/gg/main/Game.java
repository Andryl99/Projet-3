package com.gg.main;
import com.gg.joueurs.*;

public abstract class Game {

	protected int solutionLength;
	protected int nbTurns;

	protected Player attacker;
	protected Player defensor;


	public Game(int nbTurns, int solutionLength, Player attacker, Player defensor) {
		
		this.nbTurns = nbTurns;
		this.solutionLength = solutionLength;
		this.attacker = attacker;
		this.defensor = defensor;
	}

	public abstract void defensorSelectSolution();

	public abstract void attackerPlay();

	public abstract void defensorAnswer();
	
	public abstract EndGameState nextTurn();

}
