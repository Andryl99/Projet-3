package com.gg.comportement;

import java.util.Scanner;

public class ComportementHumain implements Play {
	
	private Scanner scanner = new Scanner(System.in);

	@Override
	public String JouerUnCoup(String reponse, String derniereProposition, int nbCases) {
		// TODO controle de saisie
		return scanner.nextLine();
	}
	
	@Override
	public String ChoisirSolution(int nbCases) {
		// TODO controle de saise
		System.out.println("Entrez une combinaison de " + nbCases + " chiffres à trouver.");
		return scanner.nextLine();
	}
}
