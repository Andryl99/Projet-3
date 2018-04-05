package com.gg.main.games;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.gg.main.players.AIPlayerMoreLess;
import com.gg.main.players.HumanPlayerMoreLess;
import com.gg.main.players.Player;

public class MoreLessGame extends Game {

	static final Logger logger = LogManager.getLogger();

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
		if (defensor instanceof HumanPlayerMoreLess) {
			String verification = AIPlayerMoreLess.Corrector(proposition, solution);
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
