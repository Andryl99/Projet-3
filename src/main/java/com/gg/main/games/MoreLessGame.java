package com.gg.main.games;
import com.gg.main.EndGameState;
import com.gg.main.players.*;
public class MoreLessGame extends Game {


	private String solution; // Solution du jeu
	private String answer; // Donn�e par le defenseur +-=+
	private String proposition; // Faite par l'attaquant 4549

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

	}

	@Override
	public void defensorAnswer() {
		this.answer = defensor.giveAnswer(proposition, solution, solutionLength);
	}

	@Override
	public EndGameState stateOfGame() {
		if (proposition.equals(solution))
			return EndGameState.ATTAQUANTGAGNE;
		else if (!(proposition.equals(solution)) && (this.turnCounter == this.nbTurns))
			return EndGameState.DEFENSEURGAGNE;
		else return EndGameState.AUCUNGAGNANT;		
	}

	@Override
	public boolean nextTurn() {
		// TODO Auto-generated method stub
		this.attackerPlay();
		this.defensorAnswer();
		turnCounter++;
		return (proposition.equals(solution) || turnCounter == nbTurns);
	}

}
