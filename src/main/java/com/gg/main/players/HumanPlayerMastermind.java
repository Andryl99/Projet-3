package com.gg.main.players;

import com.gg.main.ConfigurationClass;

public class HumanPlayerMastermind extends Player {

	protected String arePresents;
	protected String areAtRightPlace;

	public HumanPlayerMastermind(ConfigurationClass config) {
		super(config);
	}

	@Override
	public String selectSolution() {
		System.out.print("Entrez une combinaison de " + config.getSolutionLength() + " chiffres de 0 à "+ (config.getNbColors()-1) +" : ");
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
		System.out.println("Corrigez la combinaison suivante : " + proposition + "\t\tSolution : " + solution);
		System.out.print("Correcte(s)   : ");
		arePresents = this.inputErrorCheck(config.getSolutionLength(), config.getNbColors(), SequenceType.ISMASTERMINDCORRECTION);
		System.out.print("Bien placé(s) : ");
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
		System.out.print("Proposition : ");
		return this.inputErrorCheck(config.getSolutionLength(), config.getNbColors(), SequenceType.ISCOMBINATION);
	}
}
