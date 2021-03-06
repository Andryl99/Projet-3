package com.gg.proj.players;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.gg.proj.ConfigurationClass;

public class HumanPlayerMastermind extends Player {

	static Logger logger = LogManager.getLogger();
	protected String correct;
	protected String wellPlaced;

	public HumanPlayerMastermind(ConfigurationClass config) {
		super(config);
	}

	@Override
	public String selectSolution() {
		logger.info("Entrez une combinaison de " + config.getSolutionLength() + " chiffres de 0 à "+ (config.getNbColors()-1) +" : ");
		return this.inputErrorCheck(config.getSolutionLength(), config.getNbColors(), SequenceType.ISCOMBINATION);
	}

	@Override
	public String giveAnswer(String proposition, String solution) {
		logger.info("Corrigez la combinaison suivante : " + proposition + "\n");
		logger.info("Correcte(s)   : ");
		correct = this.inputErrorCheck(config.getSolutionLength(), config.getNbColors(), SequenceType.ISMASTERMINDCORRECTION);
		logger.info("Bien placé(s) : ");
		wellPlaced = this.inputErrorCheck(config.getSolutionLength(), config.getNbColors(), SequenceType.ISMASTERMINDCORRECTION);
		return correct + " " + wellPlaced;
	}

	@Override
	public String play(String reponse) {
		logger.info("Proposition : ");
		return this.inputErrorCheck(config.getSolutionLength(), config.getNbColors(), SequenceType.ISCOMBINATION);
	}
	
	public String toString() {
		return "joueur humain";
	}
}
