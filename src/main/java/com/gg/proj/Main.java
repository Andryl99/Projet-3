package com.gg.proj;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.sun.xml.internal.txw2.IllegalAnnotationException;

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
				if (args.length > 1) {
					throw new IllegalArgumentException("Un seul argument à la fois peut être passé");
				}

				//
				// On recupere la valeur du premier arguments
				firstArg = args[0];

				// Si cet argument est "dev" devMod est vrai
				if (firstArg.equals("dev")) {
					devMod = true;
					logger.info("Le mode développeur est activé\n");
				} else
					throw new IllegalArgumentException("L'argument doit etre \"dev\"");
			} catch (IllegalArgumentException e) {
				logger.error(e.getMessage());
			}

		Menu menu = new Menu();
		Launcher launcher = new Launcher(devMod);

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