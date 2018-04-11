package com.gg.main;

import org.apache.logging.log4j.*;

public class Main {

	static final Logger logger = LogManager.getLogger();

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

				// Si cet argument est "dev" devMod est vrai
				if (firstArg.equals("dev"))
					devMod = true;

			} catch (Exception e) {
				logger.error(e.getMessage());
			}

		Menu menu = new Menu();
		Launcher launcher = new Launcher(devMod);
		
		do {
			// try catch illegal argument exception
			// utiliser e.getMessage();
			gameChoice = menu.runGameMenu();
			modChoice = menu.runModMenu();
			do {
				launcher.runGame(gameChoice, modChoice);
				endGameChoice = menu.runEndGameMenu();
			} while (endGameChoice == 1);
		} while (endGameChoice != 3);

	}
}