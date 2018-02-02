package com.gg.main;

import java.util.InputMismatchException;
import java.util.Scanner;

import com.gg.joueurs.*;

public class Menu {

	// Consultation du fichier config, PEUT ETRE mieux dans main
	private Configuration config;

	public Menu() {
		this.config = new Configuration(6, 4, 5, false);
	}

	public void lancerMenu() {

		int choixJeu;
		int choixMode;
		int choixFinal;
		boolean exit = false;

		while (!exit) {

			choixJeu = -1;
			choixMode = -1;
			choixFinal = -1;

			System.out.println("multi-jeux");
			System.out.println("--------------------");

			//
			// Choix du jeu
			do {

				System.out.println("Choisissez un jeu");
				System.out.println("1 - RecherchePM");
				System.out.println("2 - Mastermind");

				choixJeu = this.saisieInt();

			} while (choixJeu < 1 || choixJeu > 2);

			System.out.println("--------------------");

			//
			// Choix du mode
			do {

				System.out.println("Choisissez un mode");
				System.out.println("1 - Challenger");
				System.out.println("2 - Defenseur");
				System.out.println("3 - Duel");

				choixMode = this.saisieInt();

			} while (choixMode != 1 && choixMode != 2 && choixMode != 3);

			System.out.println("--------------------");

			//
			// Création du jeu
			
			
			//
			// Menu apres jeu
			do {
				
				ModeDeJeu jeu = this.creerJeu(choixJeu, choixMode);
				jeu.defenseurChoisiSolution();
				
				//
				// Boucle du jeu
				do {
					jeu.attaquantJoue();
					jeu.defenseurRepond();
					if (jeu.tourSuivant().equals(EtatFinDeManche.AUCUNGAGNANT)) {
						continue;
					} else if (jeu.tourSuivant().equals(EtatFinDeManche.ATTAQUANTGAGNE)) {
						System.out.println("Attaquant gagne!");
						break;
					} else if (jeu.tourSuivant().equals(EtatFinDeManche.DEFENSEURGAGNE)) {
						System.out.println("Défenseur gagne!");
						break;
					}
				} while (true);

				System.out.println("--------------------");
				System.out.println("Voulez vous :");
				System.out.println("1 - Rejouer");
				System.out.println("2 - Retourner au menu");
				System.out.println("3 - Quitter l'application ?");

				choixFinal = this.saisieInt();

				if (choixFinal == 1) {
					continue;
				} else if (choixFinal == 2) {
				} else if (choixFinal == 3) {
					exit = true;
				}

			} while (choixFinal != 2 && choixFinal != 3);
		}
	}

	private ModeDeJeu creerJeu(int choixJeu, int choixMode) {
		if (choixJeu == 1) {
			Player joueur1  = new HumanPlayer();
			Player joueur2 = new AIPlayer(config.getNbCases());
			if (choixMode == 1) {
				return new RecherchePlusMoins(config.getNbCoups(), config.getNbCases(), joueur1, joueur2);
			}
			else if (choixMode == 2) {
				return new RecherchePlusMoins(config.getNbCoups(), config.getNbCases(), joueur2, joueur1);
			}
		} else if (choixJeu == 2) {
			System.out.println("Mastermind est toujours en développement !");
			return null;
		}
		return null;
	}

	public int saisieInt() {

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
}
