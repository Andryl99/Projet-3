package com.gg.joueurs;

import java.util.Scanner;

public abstract class Player {
	Scanner scanner = new Scanner(System.in);
	
	public abstract String choisitSolution(int nbCases);
	public abstract String donneReponse(String proposition, String solution, int nbCases);
	public abstract String joueUnCoup(String reponse, String derniereProposition, int nbCases);

}
