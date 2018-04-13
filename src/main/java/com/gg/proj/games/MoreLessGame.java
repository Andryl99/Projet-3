package com.gg.proj.games;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.gg.proj.players.AIPlayerMoreLess;
import com.gg.proj.players.HumanPlayerMoreLess;
import com.gg.proj.players.Player;

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
			String verification = MoreLessGame.Corrector(proposition, solution);
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
	
	public static String Corrector(String guess, String code) {
		int digitProp;
		int digitSol;
		String verification = "";

		for (int i = 0; i < guess.length(); i++) {

			digitProp = Integer.parseInt(String.valueOf(guess.charAt(i)));
			digitSol = Integer.parseInt(String.valueOf(code.charAt(i)));

			if (digitProp == digitSol)
				verification += "=";
			else if (digitProp < digitSol)
				verification += "+";
			else if (digitProp > digitSol)
				verification += "-";
		}

		return verification;
	}
}
