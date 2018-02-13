package com.gg.main;

public class Main {

	public static void main(String[] args) {
		boolean devMod = false;
		String firstArg;
		
		int gameChoice = -1;
		int modChoice = -1;
		int endGameChoice = -1;

		if (args.length > 0)
			try {
				//
				// On recupere la valeur du premier arguments
				firstArg = args[0];

				//
				// Si cet argument est "dev" devMod est vrai
				if (firstArg.equals("dev"))
					devMod = true;

			} catch (Exception e) {
			}
		//
		// TODO pour l'instant le devmod n'est pas implement�
		Menu menu = new Menu();
		// menu.runMenu();

		Launcher launcher = new Launcher();
		do {
			gameChoice = menu.runGameMenu();
			modChoice = menu.runModMenu();
			do {
				launcher.runGame(gameChoice, modChoice);
				endGameChoice = menu.runEndGameMenu();
			} while (endGameChoice == 1);
		} while (endGameChoice != 3);

	}
}