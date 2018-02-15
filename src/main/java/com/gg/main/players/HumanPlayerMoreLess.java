package com.gg.main.players;

import com.gg.main.ConfigurationClass;

public class HumanPlayerMoreLess extends Player {

	public HumanPlayerMoreLess(ConfigurationClass config) {
		super(config);
	}

	@Override
	public String selectSolution() {
		System.out.print("Entrez une combinaison de " + config.getSolutionLength() + " chiffres à trouver : ");
		return this.inputErrorCheck(config.getSolutionLength(), 10 ,SequenceType.ISCOMBINATION);
	}

	@Override
	public String giveAnswer(String proposition, String solution) {
		System.out.println("Corrigez la combinaison suivante : " + proposition + "\t\tSolution : " + solution);
		System.out.print("Correction : ");
		return this.inputErrorCheck(config.getSolutionLength(), 10 ,SequenceType.ISCORRECTION); 
	}

	@Override
	public String play(String reponse) {
		System.out.print("Proposition : ");
		return this.inputErrorCheck(config.getSolutionLength(), 10 ,SequenceType.ISCOMBINATION);
	}
}
