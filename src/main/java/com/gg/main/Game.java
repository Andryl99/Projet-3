package com.gg.main;
import com.gg.joueurs.*;

public abstract class Game {

	protected int nbCases;
	protected int nbCoups;

	protected Player attacker;
	protected Player defensor;


	public Game(int nbCoups, int nbCases, Player attacker, Player defensor) {
		
		this.nbCoups = nbCoups;
		this.nbCases = nbCases;
		this.attacker = attacker;
		this.defensor = defensor;
	}

	public abstract void defensorSelectSolution();

	public abstract void attackerPlay();

	public abstract void defensorAnswer();

	public abstract EndGameState nextTurn();

}
