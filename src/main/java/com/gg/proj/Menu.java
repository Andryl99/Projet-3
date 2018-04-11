package com.gg.proj;

import java.util.InputMismatchException;
import java.util.Scanner;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Menu {

	static final Logger logger = LogManager.getLogger();

	public Menu() {
		displayMenuHeader();
	}

	public int runGameMenu() {
		int gameChoice;
		// Choix du jeu
		do {
			displayGameList();
			gameChoice = this.intInput();
		} while (gameChoice < 1 || gameChoice > 2);
		return gameChoice;
	}

	public int runModMenu() {
		int modChoice;
		// Choix du mode de jeu
		do {
			displayModList();
			modChoice = this.intInput();
		} while (modChoice < 1 || modChoice > 3);
		return modChoice;
	}

	public int runEndGameMenu() {
		int endGameChoice;
		do {
			displayEndGameMenu();
			endGameChoice = this.intInput();
		} while (endGameChoice < 1 || endGameChoice > 3);
		return endGameChoice;
	}

	private int intInput() {
		
		int choix = -1;
		
		do {
			logger.info("Choix : ");
			@SuppressWarnings("resource")
			Scanner scanner = new Scanner(System.in);
			try {
				choix = scanner.nextInt();

			} catch (InputMismatchException e) {
				logger.warn("Erreur de saisie, recommencez\n");
			}
		} while (choix == -1);
		return choix;
	}

	private void displayMenuHeader() {
		logger.info("--------------------\n");
		logger.info("|    Mastermind    |\n");
		logger.info("|   Plus ou Moins  |\n");
	}

	private void displayGameList() {
		logger.info("--------------------\n");
		logger.info("Choisissez un jeu\n");
		logger.info("1 - Jeu du Plus ou Moins\n");
		logger.info("2 - Mastermind\n");
	}

	private void displayModList() {
		logger.info("--------------------\n");
		logger.info("Choisissez un mode\n");
		logger.info("1 - Challenger\n");
		logger.info("2 - Defenseur\n");
		logger.info("3 - Duel\n");
	}

	private void displayEndGameMenu() {
		logger.info("--------------------\n");
		logger.info("Voulez vous :\n");
		logger.info("1 - Rejouer\n");
		logger.info("2 - Retourner au menu\n");
		logger.info("3 - Quitter l'application ?\n");
	}
}
