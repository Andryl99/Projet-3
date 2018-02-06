package com.gg.joueurs;

import java.util.Scanner;

public abstract class Player {
	Scanner scanner = new Scanner(System.in);
	
	public abstract String selectSolution(int nbCases);
	public abstract String giveAnswer(String proposition, String solution, int nbCases);
	public abstract String play(String reponse, int nbCases);

}
