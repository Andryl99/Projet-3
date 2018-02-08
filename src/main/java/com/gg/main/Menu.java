package com.gg.main;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import com.gg.main.games.Game;
import com.gg.main.games.GameFactory;
import com.gg.main.games.MoreLessGame;
import com.gg.main.players.AIPlayer;
import com.gg.main.players.HumanPlayer;
import com.gg.main.players.Player;
import com.gg.main.players.PlayerFactory;

public class Menu {

	// Consultation du fichier config, PEUT ETRE mieux dans main
	private ConfigurationClass config;

	public Menu() {
		this.config = new ConfigurationClass(6, 4, 5, false);
	}

	public void runMenu() {
		int gameChoice;
		int modChoice;
		int endGameChoice;
		boolean exit = false;
		boolean isLastTurn = false;

		while (!exit) {

			gameChoice = -1;
			modChoice = -1;
			endGameChoice = -1;
			displayMenuHeader();

			// Choix du jeu
			do {
				displayGameList();
				gameChoice = this.intInput();

			} while (gameChoice < 1 || gameChoice > 2);

			// Choix du mode
			do {
				displayModList();
				modChoice = this.intInput();

			} while (modChoice != 1 && modChoice != 2 && modChoice != 3);

			// Création du jeu
			do {
				PlayerFactory playerFactory = new PlayerFactory();
				GameFactory gameFactory = new GameFactory();
				
				List<Game> gl = gameFactory.getAListOfGames(gameChoice, modChoice) ;
				
//				Game game = this.createGame(gameChoice, modChoice);				
//				game.defensorSelectSolution();

				
				for (Game game : gl) {
					game.defensorSelectSolution();
				}
				// Boucle du jeu
//				do {
//					game.attackerPlay();
//					game.defensorAnswer();
//					if (game.stateOfGame().equals(EndGameState.AUCUNGAGNANT)) {
//						continue;
//					} else if (game.stateOfGame().equals(EndGameState.ATTAQUANTGAGNE)) {
//						System.out.println("Attaquant gagne!");
//						break;
//					} else if (game.stateOfGame().equals(EndGameState.DEFENSEURGAGNE)) {
//						System.out.println("Défenseur gagne!");
//						break;
//					}
//				} while (true);
				do {
					for (Game game : gl) {
						isLastTurn = game.nextTurn();
						if( isLastTurn) break;
					}
					
				} while (!isLastTurn);
				
				
				switch (gl.get(0).stateOfGame()) {
				case AUCUNGAGNANT : 
					break;
				case ATTAQUANTGAGNE :
					System.out.println("Attaquant gagne!");	
					break;
				case DEFENSEURGAGNE :
					System.out.println("Défenseur gagne!");
					break;
				}
				
				
				displayEndGameMenu();
				endGameChoice = this.intInput();

				if (endGameChoice == 1) {
					continue;
				} else if (endGameChoice == 3) {
					exit = true;
				}

			} while (endGameChoice == 1 );
		}
	}

	private Game createGame(int gameChoice, int modChoice) {
		if (gameChoice == 1) {
			Player player1 = new HumanPlayer();
			Player player2 = new AIPlayer(config.getSolutionLength());
			if (modChoice == 1) {
				return new MoreLessGame(config.getNbTurns(), config.getSolutionLength(), player1, player2);
			} else if (modChoice == 2) {
				return new MoreLessGame(config.getNbTurns(), config.getSolutionLength(), player2, player1);
			}
		} else if (gameChoice == 2) {
			System.out.println("Mastermind est toujours en développement !");
			return null;
		}
		return null;
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
