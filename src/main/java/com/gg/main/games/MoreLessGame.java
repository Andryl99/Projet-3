package com.gg.main.games;

import com.gg.main.players.HumanPlayerMoreLess;
import com.gg.main.players.Player;

public class MoreLessGame extends Game {



	/*
	 * TODO Supprimer les arguments nbTurns et SolutionLength
	 * 
	 */
	public MoreLessGame(int nbTurns, int solutionLength, Player attacker, Player defensor) {

		super(nbTurns, solutionLength, attacker, defensor);
		this.solution = "";
		this.answer = "";
		this.proposition = "";
	}

	@Override
	public void defensorSelectSolution() {
		this.solution = defensor.selectSolution();
	}

	@Override
	public void attackerPlay() {
		this.proposition = attacker.play(answer);
	}

	@Override
	public void defensorAnswer() {
		int digitProp;
		int digitSol;
		String verification = "";

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
		if (defensor instanceof HumanPlayerMoreLess) {
			do {
				this.answer = defensor.giveAnswer(proposition, solution);
				if (!verification.equals(answer)) {
					System.out.println("Erreur sur la correction, recommencez.");
				}
			} while (!verification.equals(answer));
		} else {
			this.answer = verification;
			System.out.println("Proposition : " + proposition + "\tSolution : " + solution + "\t\t==> " + answer);
		}
	}
}
