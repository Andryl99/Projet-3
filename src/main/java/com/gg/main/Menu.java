package com.gg.main;

import java.util.InputMismatchException;
import java.util.Scanner;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Menu {

	// Consultation du fichier config, PEUT ETRE mieux dans main
	static final Logger logger = LogManager.getLogger();
	
	public Menu() {	}

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
	
	public int intInput() {
		// ne pas quiiter la  méthode tant que le choix est -1
		int choix = -1;
		System.out.print("Choix : ");
		Scanner scanner = new Scanner(System.in);
		try {
			choix = scanner.nextInt();

		} catch (InputMismatchException e) {
			System.out.println("Erreur de saisie, recommencez");
		}
		return choix;
	}

	// Des méthodes simple pour
	public void displayMenuHeader() {
		logger.info("multi-jeux");
	}

	public void displayGameList() {
		logger.info("--------------------");
		logger.info("Choisissez un jeu");
		logger.info("1 - Jeu du Plus ou Moins");
		logger.info("2 - Mastermind");
	}

	public void displayModList() {
		logger.info("--------------------");
		logger.info("Choisissez un mode");
		logger.info("1 - Challenger");
		logger.info("2 - Defenseur");
		logger.info("3 - Duel");
	}

	public void displayEndGameMenu() {
		logger.info("--------------------");
		logger.info("Voulez vous :");
		logger.info("1 - Rejouer");
		logger.info("2 - Retourner au menu");
		logger.info("3 - Quitter l'application ?");
	}
}
