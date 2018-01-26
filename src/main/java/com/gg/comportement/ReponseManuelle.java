package com.gg.comportement;

import java.util.Scanner;

public class ReponseManuelle implements Answer {

	Scanner scanner = new Scanner(System.in);
	
	@Override
	public String DonnerReponse(String proposition, String solution, int nbCases) {
		boolean ok;
		String reponse;
		
		
		System.out.println("Corrigez la proposition de l'attaquant : " + proposition + "\t reponse : " + solution);
		
		// La boucle est du type do while, on sort du la boucle si le boolean Ok est encore vrai apres tout les tests;
		do {
			ok = true;
			reponse = "";
			// Le joueur indique une réponse
			reponse = scanner.nextLine();

			// On vérifie que la combinaison est acceptable
			// On test si la longueur va bien
			if (reponse.length() == nbCases) {
				
				// On test si les caractère sont acceptables
				for (int i = 0; i < nbCases; i++) {
					if (!(reponse.charAt(i) == '-' || reponse.charAt(i) == '+' || reponse.charAt(i) == '=')) {
						ok = false;
						System.out.println("Erreur sur le type du caractère n°" + (i+1));
					}
				}
			}
			
			else {
				ok = false;
				System.out.println("Erreur sur le nombre de caractères length : " + reponse.length() + ", il en faut " + nbCases);
			}
			
		} while (!ok);
		
		return reponse;
	}
}
