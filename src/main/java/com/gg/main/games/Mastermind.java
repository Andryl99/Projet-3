package com.gg.main.games;

import com.gg.main.EndGameState;
import com.gg.main.players.Player;

public class Mastermind extends Game {

	public Mastermind(int nbTurns, int solutionLength, int nbColors, Player attacker, Player defensor) {
		super(nbTurns, solutionLength, attacker, defensor);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void defensorSelectSolution() {
		// TODO Auto-generated method stub	
	}

	@Override
	public void attackerPlay() {
		// TODO Auto-generated method stub		
	}

	@Override
	public void defensorAnswer() {
		// TODO Auto-generated method stub		
	}

	@Override
	public EndGameState stateOfGame() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean nextTurn() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void reset() {
		// TODO Auto-generated method stub
		
	}
	
}
