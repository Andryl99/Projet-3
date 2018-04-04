package com.gg.main.games;

import com.gg.main.players.AIPlayerMastermind;
import com.gg.main.players.HumanPlayerMastermind;
import com.gg.main.players.Player;

public class Mastermind extends Game {

	public Mastermind(int nbTurns, int solutionLength, int nbColors, Player attacker, Player defensor) {
		super(nbTurns, solutionLength, attacker, defensor);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void defensorSelectSolution() {
		solution = defensor.selectSolution();
	}

	@Override
	public void attackerPlay() {
		this.proposition = attacker.play(answer);
	}

	@Override
	public void defensorAnswer() {
		if (defensor instanceof HumanPlayerMastermind) {
			String verification = AIPlayerMastermind.Corrector(proposition, solution);
			do {
				this.answer = defensor.giveAnswer(proposition, solution);
				if (!verification.equals(answer)) {
					System.out.println("Erreur sur la correction, recommencez.");
				}
			} while (!verification.equals(answer));

		} else {
			this.answer = defensor.giveAnswer(proposition, solution);
		}
	}
}
