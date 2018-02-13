package com.gg.main;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import com.gg.main.games.Game;
import com.gg.main.games.GameFactory;

public class Menu {

	// Consultation du fichier config, PEUT ETRE mieux dans main

	public Menu() {	}

//	public void runMenu() {
//		/*
//		 * 
//		 *  O
//		 *  B
//		 *  S
//		 *  O
//		 *  L
//		 *  E
//		 *  T
//		 *  E 
//		 * 
//		 * 
//		 */
//		int gameChoice;
//		int modChoice;
//		int endGameChoice;
//		boolean exit = false;
//		boolean isLastTurn = false;
//
//		while (!exit) {
//
//			gameChoice = -1;
//			modChoice = -1;
//			endGameChoice = -1;
//			displayMenuHeader();
//
//			GameFactory gameFactory = new GameFactory(config);
//			Game lastGame;
//			// Choix du jeu
//			do {
//				displayGameList();
//				gameChoice = this.intInput();
//			} while (gameChoice < 1 || gameChoice > 2);
//
//			// Choix du mode de jeu
//			do {
//				displayModList();
//				modChoice = this.intInput();
//			} while (modChoice != 1 && modChoice != 2 && modChoice != 3);
//
//			// Création du jeu
//			List<Game> gameList = gameFactory.getAListOfGames(gameChoice, modChoice);
//			
//			lastGame = gameList.get(0);
//			// Boucle du jeu
//			do {
//				for (Game game : gameList) {
//					game.reset();
//					game.defensorSelectSolution();
//				}
//
//				// On enchaine les tours ...
//				do {
//					for (Game game : gameList) {
//						lastGame = game;
//						isLastTurn = game.nextTurn();
//						if (isLastTurn)
//							break;
//					}
//				} while (!isLastTurn);
//
//				// TODO ... est buggé en duel
//				switch (lastGame.stateOfGame()) {
//				case AUCUNGAGNANT:
//					break;
//				case ATTAQUANTGAGNE:
//					System.out.println("Attaquant gagne!");
//					break;
//				case DEFENSEURGAGNE:
//					System.out.println("Défenseur gagne!");
//					break;
//				default :
//					break; 
//				}
//
//				displayEndGameMenu();
//				endGameChoice = this.intInput();
//				// Gestion fin de partie
//				if (endGameChoice == 1) {
//					continue;
//				} else if (endGameChoice == 3) {
//					exit = true;
//				}
//			} while (endGameChoice == 1);
//		}
//	}

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
		System.out.println("multi-jeux");
	}

	public void displayGameList() {
		System.out.println("--------------------");
		System.out.println("Choisissez un jeu");
		System.out.println("1 - Jeu du Plus ou Moins");
		System.out.println("2 - Mastermind");
	}

	public void displayModList() {
		System.out.println("--------------------");
		System.out.println("Choisissez un mode");
		System.out.println("1 - Challenger");
		System.out.println("2 - Defenseur");
		System.out.println("3 - Duel");
	}

	public void displayEndGameMenu() {
		System.out.println("--------------------");
		System.out.println("Voulez vous :");
		System.out.println("1 - Rejouer");
		System.out.println("2 - Retourner au menu");
		System.out.println("3 - Quitter l'application ?");
	}
}
