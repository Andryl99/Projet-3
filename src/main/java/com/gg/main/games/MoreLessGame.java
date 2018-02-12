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
			int digitProp;
			int digitSol;
			String verification ="";
			
			for (int i = 0; i < solutionLength; i++) {

				digitProp = Integer.parseInt(String.valueOf(proposition.charAt(i)));
				digitSol = Integer.parseInt(String.valueOf(solution.charAt(i)));

				if (digitProp == digitSol)
					verification += "=";
				else if (digitProp < digitSol)
					verification += "+";
				else if (digitProp > digitSol)
					verification += "-";
			}
			if (defensor instanceof HumanPlayer) {
				do {
			this.answer = defensor.giveAnswer(proposition, solution, solutionLength);
				} while (!verification.equals(answer));
			}
			else {
				this.answer = verification;
				System.out.println("Proposition : " + proposition + "\tSolution : " + solution + "\t\t==> " + answer);
			}
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
		this.attackerPlay();
		this.defensorAnswer();
		turnCounter++;
		return (proposition.equals(solution) || turnCounter == nbTurns);
	}

	@Override
	public void reset() {
		this.turnCounter = 0;
		this.solution = "";
		this.answer = "";
		this.proposition = "";
	}
}
