package com.gg.main;
import com.gg.joueurs.*;
public class MoreLessGame extends Game {


	private String solution; // Solution du jeu
	private String reponse; // Donn�e par le defenseur +-=+
	private String proposition; // Faite par l'attaquant 4549
	private int turnCount = 0;

	public MoreLessGame(int turnNb, int nbCases, Player attacker, Player defensor) {
		
		super(turnNb, nbCases, attacker, defensor);
		this.solution = "";
		this.reponse = "";
		this.proposition = "";
	}
		
	@Override
	public void defensorSelectSolution() {
		this.solution = defensor.selectSolution(nbCases);
	}

	@Override
	public void attackerPlay() {
		this.proposition = attacker.play(reponse, nbCases);
		turnCount++;
	}

	@Override
	public void defensorAnswer() {
		this.reponse = defensor.giveAnswer(proposition, solution, nbCases);
	}

	@Override
	public EndGameState nextTurn() {
		if (proposition.equals(solution))
			return EndGameState.ATTAQUANTGAGNE;
		else if (!(proposition.equals(solution)) && (this.turnCount == this.nbCoups))
			return EndGameState.DEFENSEURGAGNE;
		else return EndGameState.AUCUNGAGNANT;		
	}
}
