package com.gg.main;
import com.gg.joueurs.*;
public class MoreLessGame extends Game {


	private String solution; // Solution du jeu
	private String answer; // Donn�e par le defenseur +-=+
	private String proposition; // Faite par l'attaquant 4549
	private int turnCount = 0;

	public MoreLessGame(int nbTurns, int solutionLength, Player attacker, Player defensor) {
		
		super(nbTurns, solutionLength, attacker, defensor);
		this.solution = "";
		this.answer = "";
		this.proposition = "";
	}
		
	@Override
	public void defensorSelectSolution() {
		this.solution = defensor.selectSolution(solutionLength);
	}

	@Override
	public void attackerPlay() {
		this.proposition = attacker.play(answer, solutionLength);
		turnCount++;
	}

	@Override
	public void defensorAnswer() {
		this.answer = defensor.giveAnswer(proposition, solution, solutionLength);
	}

	@Override
	public EndGameState nextTurn() {
		if (proposition.equals(solution))
			return EndGameState.ATTAQUANTGAGNE;
		else if (!(proposition.equals(solution)) && (this.turnCount == this.nbTurns))
			return EndGameState.DEFENSEURGAGNE;
		else return EndGameState.AUCUNGAGNANT;		
	}

}
