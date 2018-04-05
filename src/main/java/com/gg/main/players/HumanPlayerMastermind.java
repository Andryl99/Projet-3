package com.gg.main.players;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.gg.main.ConfigurationClass;

public class HumanPlayerMastermind extends Player {

	static Logger logger = LogManager.getLogger();
	protected String arePresents;
	protected String areAtRightPlace;

	public HumanPlayerMastermind(ConfigurationClass config) {
		super(config);
	}

	@Override
	public String selectSolution() {
		logger.info("Entrez une combinaison de " + config.getSolutionLength() + " chiffres de 0 à "+ (config.getNbColors()-1) +" : ");
		return this.inputErrorCheck(config.getSolutionLength(), config.getNbColors(), SequenceType.ISCOMBINATION);
	}

	/*
	 * 
	 * giveAnswer OU giveMasterMindAnswer
	 * 			Pas les deux
	 * 
	 */

	/*
	 * 
	 * TODO Factorisation de code
	 * 
	 * */
	@Override
	public String giveAnswer(String proposition, String solution) {
		logger.info("Corrigez la combinaison suivante : " + proposition + "\n");
		logger.debug("Solution : " + solution +"\n");
		logger.info("Correcte(s)   : ");
		arePresents = this.inputErrorCheck(config.getSolutionLength(), config.getNbColors(), SequenceType.ISMASTERMINDCORRECTION);
		logger.info("Bien placé(s) : ");
		areAtRightPlace = this.inputErrorCheck(config.getSolutionLength(), config.getNbColors(), SequenceType.ISMASTERMINDCORRECTION);
		return arePresents + " " + areAtRightPlace;
	}

//	public Pair<String, String> giveMastermindAnswer(String proposition, String solution) {
//		System.out.println("Corrigez la combinaison suivante : " + proposition + "\t\tSolution : " + solution);
//		System.out.print("Nombre de présents : ");
//		arePresents = scanner.nextLine();
//		System.out.print("Nombre de bien placé : ");
//		areAtRightPlace = scanner.nextLine();
//		Pair<String, String> pair = new Pair<String, String>(arePresents, areAtRightPlace);
//		return pair;
//	}

	@Override
	public String play(String reponse) {
		logger.info("Proposition : ");
		return this.inputErrorCheck(config.getSolutionLength(), config.getNbColors(), SequenceType.ISCOMBINATION);
	}
	public String toString() {
		return "joueur humain";
	}
}
