package com.gg.main.players;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.gg.main.ConfigurationClass;

public class HumanPlayerMoreLess extends Player {

	static final Logger logger = LogManager.getLogger();

	public HumanPlayerMoreLess(ConfigurationClass config) {
		super(config);
	}

	@Override
	public String selectSolution() {
		logger.info("Entrez une combinaison de " + config.getSolutionLength() + " chiffres à trouver : ");
		return this.inputErrorCheck(config.getSolutionLength(), 10, SequenceType.ISCOMBINATION);
	}

	@Override
	public String giveAnswer(String proposition, String solution) {
		logger.info("Corrigez la combinaison suivante : " + proposition + "\n");
		logger.info("Correction : ");
		return this.inputErrorCheck(config.getSolutionLength(), 10, SequenceType.ISCORRECTION);
	}

	@Override
	public String play(String reponse) {
		logger.info("Proposition : ");
		return this.inputErrorCheck(config.getSolutionLength(), 10, SequenceType.ISCOMBINATION);
	}
	public String toString() {
		return "joueur humain";
	}
}
