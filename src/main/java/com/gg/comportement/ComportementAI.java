package com.gg.comportement;

import java.util.Random;

public class ComportementAI implements Play {

	@Override
	public String JouerUnCoup(String reponse, String derniereProposition, int nbCases) {

		String proposition = "";

		if (reponse == "") {
			for (int i = 0; i < nbCases; i++)
				proposition += "5";
			return proposition;
		}

		//
		// J'ai récupéré la derniere combinaison ce qui donne une IA simple qui ajoute
		// ou soustraie 1 au dernier coup pour s'approcher de la solution
		for (int i = 0; i < nbCases; i++) {
			
			// Le switch cases ne marche pas
			
			// switch (reponse.charAt(i))
			// {
			// case '+' :
			// proposition += (char)(derniereProposition.charAt(i) + 1) ;
			// System.out.println(proposition);
			// case '-' :
			// proposition += (char)(derniereProposition.charAt(i) - 1) ;
			// System.out.println(proposition);
			// case '=' :
			// proposition += derniereProposition.charAt(i) ;
			// }
			
			if (reponse.charAt(i) == '+') {
				proposition += (char) (derniereProposition.charAt(i) + 1);
			} 
			else if (reponse.charAt(i) == '-') {
				proposition += (char) (derniereProposition.charAt(i) - 1);
			} 
			else if (reponse.charAt(i) == '=') {
				proposition += derniereProposition.charAt(i);
			}
		}
		return proposition;
	}

	@Override
	public String ChoisirSolution(int nbCases) {
		Random rand = new Random();
		int min = 1;
		int max = 9;
		int nbRandom;
		String solution = "";

		for (int i = 0; i < nbCases; i++) {
			nbRandom = rand.nextInt(max - min + 1) + min;
			solution += String.valueOf(nbRandom);
		}
		System.out.println("l'AI a selectionné une combinaison de " + nbCases + " chiffres.");
		return solution;
	}
}
