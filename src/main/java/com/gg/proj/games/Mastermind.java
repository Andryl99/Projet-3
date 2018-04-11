package com.gg.proj.games;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.gg.proj.players.AIPlayerMastermind;
import com.gg.proj.players.HumanPlayerMastermind;
import com.gg.proj.players.Player;

public class Mastermind extends Game {

	static final Logger logger = LogManager.getLogger();
	
	public Mastermind(int nbTurns, int solutionLength, int nbColors, Player attacker, Player defensor) {
		super(nbTurns, solutionLength, attacker, defensor);
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
					logger.warn("Erreur sur la correction, recommencez.\n");
				}
			} while (!verification.equals(answer));
		} else {
			this.answer = defensor.giveAnswer(proposition, solution);
		}
	}
}
